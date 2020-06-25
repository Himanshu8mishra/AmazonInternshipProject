package lambda;

//Calls Method LambdaExample::deleteFunction()
//Deletes the lambda function
public class DeleteFunction
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another method
            //which returns reference of an object of class LambdaExample
            LambdaExample testObject = getNewLambdaExample.newLambdaExample();

            String functionName = args[0];

            testObject.deleteFunction(functionName);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
