package lambda;

import software.amazon.awssdk.services.lambda.model.FunctionCode;

import java.io.InputStream;
import java.util.Properties;

/**
 * Creates new lambda function
 * @author Himanshu Mishra
 */
public class CreateFunction
{
    /**
     *
     * Calls Method LambdaOperations::createFunction()
     * @param args
     * Required variables:
     * functionName - Name of the function to be created
     */
    public static void main(String[] args)
    {
        try
        {
            LambdaOperations lambdaObject = new LambdaOperations();

            String functionName = args[0];

            //Loading data from Lambda Config file
            InputStream input =
                    CreateFunction.class.getClassLoader().getResourceAsStream("LambdaConfig.properties");

            Properties prop = new Properties();
            prop.load(input);

            String handler = prop.getProperty("handler");
            String runtime = prop.getProperty("runtime");
            String roleArn = prop.getProperty("roleArn");
            String bucketName = prop.getProperty("bucketName");
            String s3key = prop.getProperty("s3key");

            //Getting reference for the deployment package stored in s3 bucket
            FunctionCode functionCode = lambdaObject.getFunctionCode(bucketName, s3key);

            String response = lambdaObject.createFunction(functionName, handler, runtime, roleArn, functionCode);
            System.out.println(response + " created");
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
