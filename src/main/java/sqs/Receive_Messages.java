package sqs;

//Calls Method SQSOperations::receiveMessage()
//Retrieves messages from the queue
public class Receive_Messages
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing sqsObject by calling another function
            //which returns reference of an object of class SQSOperations
            SQSOperations sqsObject = getNewObject.newSqsObject();

            String queueName = args[0];
            int maxMessages = Integer.parseInt(args[1]);
            int waitTime = Integer.parseInt(args[2]);

            sqsObject.receiveMessage(queueName,maxMessages,waitTime);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
