package lambda;

import java.io.InputStream;
import java.util.Properties;

/**
 * Calls Method LambdaOperations::addTrigger()
 * Specifies event source for the lambda function
 * @author Himanshu Mishra
 */
public class AddTrigger
{
    /**
     * @param args
     * Required variables: functionName
     */
    public static void main(String[] args)
    {
        try
        {
            LambdaOperations lambdaObject = new LambdaOperations();

            String functionName = args[0];

            InputStream input =
                    CreateFunction.class.getClassLoader().getResourceAsStream("LambdaConfig.properties");

            Properties prop = new Properties();
            prop.load(input);

            String sourceArn = prop.getProperty("sourceArn");
            boolean enableTrigger = Boolean.parseBoolean(prop.getProperty("enableTrigger"));
            int messagePollingLimit = Integer.parseInt(prop.getProperty("messagePollingLimit"));

            if(messagePollingLimit < 0 || messagePollingLimit > 10)
            {
                System.out.println("Message Polling Limit is between 1 to 10 (both values included)");
                return;
            }
            
            String uuid = lambdaObject.addTrigger(functionName,sourceArn,enableTrigger,messagePollingLimit);
            System.out.println(uuid);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
