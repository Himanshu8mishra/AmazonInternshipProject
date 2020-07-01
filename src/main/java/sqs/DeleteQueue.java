package sqs;

/**
 * Calls Method SqsOperations::deleteQueue()
 * Deletes the specified queue
 * @author Himanshu Mishra
 */
public class DeleteQueue
{
    /**
     * @param args
     * Required variables: queueName
     */
    public static void main(String[] args)
    {
        try
        {
            SqsOperations sqsObject = new SqsOperations();

            String queueName = args[0];

            String response = sqsObject.deleteQueue(queueName);
            System.out.println(response + "\n" + queueName + " deleted");
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
