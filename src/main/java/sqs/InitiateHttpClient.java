package sqs;

import java.io.InputStream;
import java.util.Properties;

/**
 * Calls Method HttpMessageClient::messageProducer()
 * which creates a cycle of sending message to SQS and
 * receiving response from Lambda function
 * @author Himanshu Mishra
 */
public class InitiateHttpClient
{
    /**
     * @param args
     * Required variables: queueName, message, messageGroupID
     */
    public static void main(String[] args)
    {
        try
        {
            SqsOperations sqsObject = new SqsOperations();
            String queueName = args[0];
            String message = args[1];
            String messageGroupID = args[2];

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
