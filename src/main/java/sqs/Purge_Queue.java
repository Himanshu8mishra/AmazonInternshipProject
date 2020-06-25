package sqs;

//Calls Method SQSOperations::purgeQueue()
//Deletes all the messages in the queue
public class Purge_Queue
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing sqsObject by calling another function
            //which returns reference of an object of class SQSOperations
            SQSOperations sqsObject = getNewObject.newSqsObject();

            String queueName = args[0];

            sqsObject.purgeQueue(queueName);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
