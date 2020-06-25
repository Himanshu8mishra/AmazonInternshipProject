package lambda;

import software.amazon.awssdk.services.lambda.model.FunctionCode;

//Calls Method LambdaExample::createFunction()
//Creates new lambda function
public class CreateFunction
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another method
            //which returns reference of an object of class LambdaExample
            LambdaExample testObject = getNewLambdaExample.newLambdaExample();

            String functionName = args[0];
            String handler = args[1];
            String runtime = args[2];
            String role = args[3];

            //Getting reference for the deployment package stored in s3 bucket
            FunctionCode functionCode = testObject.getFunctionCode(args[4],args[5]);

            testObject.createFunction(functionName,handler,runtime,role,functionCode);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
