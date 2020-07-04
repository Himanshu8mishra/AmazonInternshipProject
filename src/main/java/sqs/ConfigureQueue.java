package sqs;

/**
 * Changes Visibility Timeout and Polling Time of the Queue
 * @author Himanshu Mishra
 */
public class ConfigureQueue
{
    /**
     * Calls Method SqsOperations::configureQueue()
     * @param args
     * Required variables:
     * queueName - Name of the queue to be configured
     * visibilityTimeout - Time period for which queue remains invisible to other
     *                      consumers after being fetched by one of the consumer
     * pollTime - Time limit over which queue must provide messages to the consumer
     */
    public static void main(String[] args)
    {
        try
        {
            SqsOperations sqsObject = new SqsOperations();

            String queueName = args[0];
            String visibilityTimeout = args[1];
            String pollTime = args[2];

            sqsObject.configureQueue(queueName, visibilityTimeout, pollTime);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
