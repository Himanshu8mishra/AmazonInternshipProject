package sqs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueAttributesRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueAttributesResponse;
import software.amazon.awssdk.services.sqs.model.ListQueuesResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing all the methods of class SqsOperations
 * @author Himanshu Mishra
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class testingSqsOperations
{
    private static SqsClient sqsClient;
    private static final SqsOperations unitTestObject = new SqsOperations();
    private static final String queueName = "unitTestQueue.fifo";
    private static String queueUrl;

    @BeforeAll
    public static void setUp()
    {
        sqsClient = SqsClient.builder().build();
        assertNotNull(sqsClient);
    }

    @Test
    @Order(1)
    public void testingCreateFifoQueue()
    {
        queueUrl = unitTestObject.createFifoQueue(queueName);

        ListQueuesResponse listQueuesResponse = sqsClient.listQueues();
        List<String> list = listQueuesResponse.queueUrls();

        assertTrue(list.contains(queueUrl));
    }

    @Test
    @Order(2)
    public void testingConfigureQueue()
    {
        String visibilityTimeout = "10";
        String pollTime = "5";
        unitTestObject.configureQueue(queueName, visibilityTimeout, pollTime);

        List<QueueAttributeName> attributeNameCollection = new ArrayList<>();
        attributeNameCollection.add(QueueAttributeName.VISIBILITY_TIMEOUT);
        attributeNameCollection.add(QueueAttributeName.RECEIVE_MESSAGE_WAIT_TIME_SECONDS);

        GetQueueAttributesRequest getQueueAttributesRequest = GetQueueAttributesRequest.builder()
                .queueUrl(queueUrl)
                .attributeNames(attributeNameCollection)
                .build();
        GetQueueAttributesResponse getQueueAttributesResponse =
                sqsClient.getQueueAttributes(getQueueAttributesRequest);

        Map<QueueAttributeName,String> attributes = getQueueAttributesResponse.attributes();

        assertEquals("10",attributes.get(QueueAttributeName.VISIBILITY_TIMEOUT));
        assertEquals("5",attributes.get(QueueAttributeName.RECEIVE_MESSAGE_WAIT_TIME_SECONDS));
    }
    @Test
    @Order(3)
    public void testingSendMessage()
    {
        String message = "testMessage";
        String messageGroupID = "testGroup";
        String messageID = unitTestObject.sendMessage(queueName,message,messageGroupID);

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .build();
        List<Message> messageList = sqsClient.receiveMessage(receiveMessageRequest).messages();

        for(Message iterator : messageList)
        {
            assertEquals(messageID,iterator.messageId());
        }
    }

    @Test
    @Order(4)
    public void testingDeleteQueue() throws InterruptedException
    {
        unitTestObject.deleteQueue(queueName);

        //By default AWS requires maximum of 60 sec to carry out
        //delete operation on SQS queue
        Thread.sleep(60000);

        ListQueuesResponse listQueuesResponse = sqsClient.listQueues();
        List<String> list = listQueuesResponse.queueUrls();

        assertFalse(list.contains(queueUrl));
    }
}