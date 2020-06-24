package sqs;

//Calls Method SQSExample::deleteQueue()
//Deletes the specified queue
public class Delete_Queue
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another function
            //which returns reference of an object of class SQSExample
            SQSExample testObject = getNewSQSExample.newSQSExample();

            String queueName = args[0];

            testObject.deleteQueue(queueName);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
