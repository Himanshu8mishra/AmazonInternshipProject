package sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;
import software.amazon.awssdk.services.sqs.model.DeleteQueueResponse;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;
import software.amazon.awssdk.services.sqs.model.SetQueueAttributesRequest;
import software.amazon.awssdk.services.sqs.model.SetQueueAttributesResponse;

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
     * @param queueName Name of the queue to be configured
     * @param visibilityTimeout Time for which messages will be invisible after being fetched
     * @param pollTime Time limit over which queue should respond to the consumer
     * @return Response received from AWS
     */
    public String configureQueue(String queueName, String visibilityTimeout, String pollTime)
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
        SetQueueAttributesResponse setQueueAttributesResponse = sqsClient.setQueueAttributes(setQueueAttributesRequest);

        return setQueueAttributesResponse.toString();
    }

    /**
     * @param queueName Name of the queue to be deleted
     * @return Response received from AWS
     */
    public String deleteQueue(String queueName)
    {
        String queueUrl = getQueueUrl(queueName);

        System.out.println("\nDeleting queue: " + queueName);

        DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
                .queueUrl(queueUrl)
                .build();
        DeleteQueueResponse deleteQueueResponse = sqsClient.deleteQueue(deleteQueueRequest);

        return deleteQueueResponse.toString();
    }
}