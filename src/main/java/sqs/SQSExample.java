package sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;
import java.util.*;

//Contains methods for all the basic operations related to AWS SQS
public class SQSExample
{
    private static SqsClient sqsClient;

    SQSExample()
    {
        sqsClient = SqsClient.builder().build();
    }

    private static String getQueueUrl(String queueName)
    {
        GetQueueUrlResponse getQueueUrlResponse =
                sqsClient.getQueueUrl(GetQueueUrlRequest.builder().queueName(queueName).build());

        return (getQueueUrlResponse.queueUrl());
    }

    public void createStandardQueue(String queueName )
    {
        System.out.println("\nCreating queue: "+queueName);

        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName(queueName)
                .build();

        sqsClient.createQueue(createQueueRequest);

        System.out.println("Queue Created\nQueue URL = "+getQueueUrl(queueName));
    }

    public void listQueues()
    {
        System.out.println("\nQueue List");

        ListQueuesResponse listQueuesResponse = sqsClient.listQueues();

        for (String url : listQueuesResponse.queueUrls())
        {
            System.out.println(url);
        }
    }

    //Changing the visibility timeout and polling time
    //Can also be used to configure other things related to the queue
    public void configureQueue(String queueName, String visibilityTimeout, String pollTime)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nConfiguring Queue: "+queueName);

        Map<QueueAttributeName,String> attributes = new HashMap<>();
        attributes.put(QueueAttributeName.VISIBILITY_TIMEOUT,visibilityTimeout);
        attributes.put(QueueAttributeName.RECEIVE_MESSAGE_WAIT_TIME_SECONDS,pollTime);

        SetQueueAttributesRequest setQueueAttributesRequest = SetQueueAttributesRequest.builder()
                .queueUrl(queueUrl)
                .attributes(attributes)
                .build();
        sqsClient.setQueueAttributes(setQueueAttributesRequest);

        System.out.println("Queue Configured");
    }

    public void sendMessage(String queueName, String message)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nSending Message: "+message);

        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();
        sqsClient.sendMessage(sendMsgRequest);

        System.out.println("Message Sent");
    }

    public void receiveMessage(String queueName, int maxMessages, int waitTime)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nReceiving messages");

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(maxMessages)
                .waitTimeSeconds(waitTime)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();

        System.out.println("Number of messages received = "+messages.size());

        for (Message m : messages)
        {
            System.out.println(m.body());
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .receiptHandle(m.receiptHandle())
                    .build();
            sqsClient.deleteMessage(deleteMessageRequest);
        }
    }

    public void purgeQueue(String queueName)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nDeleting all the messages in the queue.");

        PurgeQueueRequest purgeQueueRequest = PurgeQueueRequest.builder()
                .queueUrl(queueUrl)
                .build();
        sqsClient.purgeQueue(purgeQueueRequest);

        System.out.println("Queue Purged");
    }

    public void deleteQueue(String queueName)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nDeleting queue.");

        DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
                .queueUrl(queueUrl)
                .build();
        sqsClient.deleteQueue(deleteQueueRequest);

        System.out.println("Queue Deleted");
    }
}