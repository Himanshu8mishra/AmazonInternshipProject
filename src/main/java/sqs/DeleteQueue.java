package sqs;

/**
 * Deletes the specified queue
 * @author Himanshu Mishra
 */
public class DeleteQueue
{
    /**
     * Calls Method SqsOperations::deleteQueue()
     * @param args
     * Required variables:
     * queueName - Name of the queue to be deleted
     */
    public static void main(String[] args)
    {
        try
        {
            SqsOperations sqsObject = new SqsOperations();

            String queueName = args[0];

            sqsObject.deleteQueue(queueName);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
