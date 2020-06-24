package sqs;

//Calls Method SQSExample::configureQueue()
//Changes Visibility Timeout and Polling Time of the Queue
public class Configure_Queue
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another function
            //which returns reference of an object of class SQSExample
            SQSExample testObject = getNewSQSExample.newSQSExample();

            String queueName = args[0];
            String visibilityTimeout = args[1];
            String pollTime = args[2];

            testObject.configureQueue(queueName, visibilityTimeout, pollTime);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
