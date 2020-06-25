package lambda;

import software.amazon.awssdk.services.lambda.model.FunctionCode;

//Calls Method LambdaOperations::createFunction()
//Creates new lambda function
public class CreateFunction
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing lambdaObject by calling another method
            //which returns reference of an object of class LambdaOperations
            LambdaOperations lambdaObject = getNewObject.newLambdaObject();

            String functionName = args[0];
            String handler = args[1];
            String runtime = args[2];
            String role = args[3];
            String bucketName = args[4];
            String s3key = args[5];

            //Getting reference for the deployment package stored in s3 bucket
            FunctionCode functionCode = lambdaObject.getFunctionCode(bucketName,s3key);

            lambdaObject.createFunction(functionName,handler,runtime,role,functionCode);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
