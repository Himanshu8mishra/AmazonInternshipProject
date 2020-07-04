package sqs;

import java.io.InputStream;
import java.util.Properties;

/**
 * Creates a cycle of sending message to SQS and
 * receiving response from Lambda function
 * @author Himanshu Mishra
 */
public class InitiateHttpClient
{
    /**
     * Calls Method HttpMessageClient::messageProducer()
     * @param args
     * Required variables:
     * queueName - Name of the queue to which messages are being sent
     * message - Message that is to be sent
     * messageGroupID - Group ID of the messages to maintain order of messages in Fifo queues
     */
    public static void main(String[] args)
    {
        try
        {
            SqsOperations sqsObject = new SqsOperations();
            String queueName = args[0];
            String message = args[1];
            String messageGroupID = args[2];

            //Loading data from SqsConfig file
            InputStream input =
                    InitiateHttpClient.class.getClassLoader().getResourceAsStream("SqsConfig.properties");

            Properties prop = new Properties();
            prop.load(input);

            int numberOfMessages = Integer.parseInt(prop.getProperty("numberOfMessages"));

            HttpMessageClient httpClientObject =
                    new HttpMessageClient(sqsObject, queueName, message, messageGroupID,numberOfMessages);
            httpClientObject.messageProducer();

            System.out.println("\nClient Connection Terminated\n");
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
