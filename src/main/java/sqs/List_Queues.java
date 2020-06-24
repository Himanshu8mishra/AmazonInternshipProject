package sqs;

//Calls Method SQSExample::listQueues()
//Lists URL of all the queues
public class List_Queues
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another function
            //which returns reference of an object of class SQSExample
            SQSExample testObject = getNewSQSExample.newSQSExample();

            testObject.listQueues();
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
