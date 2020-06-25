package lambda;

//Calls Method LambdaOperations::invokeFunction()
//Invokes the lambda function
public class InvokeFunction
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing lambdaObject by calling another function
            //which returns reference of an object of class LambdaOperations
            LambdaOperations lambdaObject = getNewObject.newLambdaObject();

            String functionName = args[0];

            String response = lambdaObject.invokeFunction(functionName);
            System.out.println(response);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
