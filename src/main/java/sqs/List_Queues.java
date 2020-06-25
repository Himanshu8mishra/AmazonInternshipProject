package sqs;

//Calls Method SQSOperations::listQueues()
//Lists URL of all the queues
public class List_Queues
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing sqsObject by calling another function
            //which returns reference of an object of class SQSOperations
            SQSOperations sqsObject = getNewObject.newSqsObject();

            sqsObject.listQueues();
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
