package sqs;

//Calls Method SQSExample::createStandardQueue()
//Creates new standard queue
public class Create_Standard_Queue
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another function
            //which returns reference of an object of class SQSExample
            SQSExample testObject = getNewSQSExample.newSQSExample();

            String queueName = args[0];

            testObject.createStandardQueue(queueName);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
