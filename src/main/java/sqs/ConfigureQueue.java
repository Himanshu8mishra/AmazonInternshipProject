package sqs;

/**
 * Calls Method SqsOperations::configureQueue()
 * Changes Visibility Timeout and Polling Time of the Queue
 * @author Himanshu Mishra
 */
public class ConfigureQueue
{
    /**
     * @param args
     * Required variables: queueName, visibilityTimeout, pollTime
     */
    public static void main(String[] args)
    {
        try
        {
            SqsOperations sqsObject = new SqsOperations();

            String queueName = args[0];
            String visibilityTimeout = args[1];
            String pollTime = args[2];

            String response = sqsObject.configureQueue(queueName, visibilityTimeout, pollTime);
            System.out.println(response + "\n" + queueName + " configured");
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
