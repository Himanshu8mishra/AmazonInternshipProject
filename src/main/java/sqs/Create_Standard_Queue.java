package sqs;

//Calls Method SQSOperations::createStandardQueue()
//Creates new standard queue
public class Create_Standard_Queue
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing sqsObject by calling another function
            //which returns reference of an object of class SQSOperations
            SQSOperations sqsObject = getNewObject.newSqsObject();

            String queueName = args[0];

            sqsObject.createStandardQueue(queueName);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
