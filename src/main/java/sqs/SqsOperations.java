package sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import software.amazon.awssdk.services.sqs.model.SetQueueAttributesRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains methods for all the basic operations related to AWS SQS
 * @author Himanshu Mishra
 */
public class SqsOperations
{
    private static SqsClient sqsClient;

    SqsOperations()
    {
        sqsClient = SqsClient.builder().build();
    }

    private static String getQueueUrl(String queueName)
    {
        GetQueueUrlResponse getQueueUrlResponse =
                sqsClient.getQueueUrl(GetQueueUrlRequest.builder().queueName(queueName).build());

        return (getQueueUrlResponse.queueUrl());
    }

    /**
     * Creates a Fifo queue in AWS
     * @param queueName Name of the queue to be created
     * @return URL of the queue created
     */
    public String createFifoQueue(String queueName)
    {
        System.out.println("\nCreating queue: " + queueName);

        Map<QueueAttributeName,String> attributes = new HashMap<>();
        attributes.put(QueueAttributeName.FIFO_QUEUE,"true");
        attributes.put(QueueAttributeName.CONTENT_BASED_DEDUPLICATION,"true");

        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName(queueName)
                .attributes(attributes)
                .build();

        CreateQueueResponse createQueueResponse = sqsClient.createQueue(createQueueRequest);

        return createQueueResponse.queueUrl();
    }

    /**
     * Configures value of visibility timeout and polling time of the queue
     * @param queueName Name of the queue to be configured
     * @param visibilityTimeout Time for which messages will be invisible after being fetched
     * @param pollTime Time limit over which queue should respond to the consumer
     */
    public void configureQueue(String queueName, String visibilityTimeout, String pollTime)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nConfiguring Queue: " + queueName);

        Map<QueueAttributeName,String> attributes = new HashMap<>();
        attributes.put(QueueAttributeName.VISIBILITY_TIMEOUT,visibilityTimeout);
        attributes.put(QueueAttributeName.RECEIVE_MESSAGE_WAIT_TIME_SECONDS,pollTime);

        SetQueueAttributesRequest setQueueAttributesRequest = SetQueueAttributesRequest.builder()
                .queueUrl(queueUrl)
                .attributes(attributes)
                .build();
        sqsClient.setQueueAttributes(setQueueAttributesRequest);

        System.out.println(queueName + " configured");
    }

    /**
     * Sends message to AWS SQS
     * @param queueName Name of the queue to which message is being sent
     * @param message Message that is to be sent
     * @param messageGroupID Group ID of the message used to maintain order of messages in Fifo queues
     * @return ID of message sent
     */
    public String sendMessage(String queueName, String message, String messageGroupID)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nSending Message: "+message);

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .messageGroupId(messageGroupID)
                .build();
        SendMessageResponse sendMessageResponse = sqsClient.sendMessage(sendMessageRequest);

        return sendMessageResponse.messageId();
    }

    /**
     * Deleted the specified queue from AWS
     * @param queueName Name of the queue to be deleted
     */
    public void deleteQueue(String queueName)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nDeleting queue: " + queueName);

        DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
                .queueUrl(queueUrl)
                .build();
        sqsClient.deleteQueue(deleteQueueRequest);

        System.out.println(queueName + " deleted");
    }
}