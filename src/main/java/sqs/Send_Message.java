package sqs;

//Calls Method SQSExample::sendMessage()
//Sends the message to the queue
public class Send_Message
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another function
            //which returns reference of an object of class SQSExample
            SQSExample testObject = getNewSQSExample.newSQSExample();

            String queueName = args[0];
            String message = args[1];

            testObject.sendMessage(queueName,message);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
       }
    }
}
