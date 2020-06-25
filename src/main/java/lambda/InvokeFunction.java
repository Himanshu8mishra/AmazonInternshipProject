package lambda;

//Calls Method LambdaExample::invokeFunction()
//Invokes the lambda function
public class InvokeFunction
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another function
            //which returns reference of an object of class LambdaExample
            LambdaExample testObject = getNewLambdaExample.newLambdaExample();

            String functionName = args[0];

            String response = testObject.invokeFunction(functionName);
            System.out.println(response);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
