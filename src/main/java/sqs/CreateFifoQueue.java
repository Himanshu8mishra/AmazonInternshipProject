package sqs;

/**
 * Calls Method SqsOperations::createFifoQueue()
 * Creates new FIFO queue
 * @author Himanshu Mishra
 */
public class CreateFifoQueue
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

            String response = sqsObject.createFifoQueue(queueName);
            System.out.println(response + "\n" + queueName + " created");
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
