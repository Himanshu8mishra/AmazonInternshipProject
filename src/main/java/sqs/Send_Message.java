package sqs;

//Calls Method SQSOperations::sendMessage()
//Sends the message to the queue
public class Send_Message
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing sqsObject by calling another function
            //which returns reference of an object of class SQSOperations
            SQSOperations sqsObject = getNewObject.newSqsObject();

            String queueName = args[0];
            String message = args[1];

            sqsObject.sendMessage(queueName,message);
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
       }
    }
}
