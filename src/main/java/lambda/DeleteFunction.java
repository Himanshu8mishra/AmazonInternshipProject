package lambda;

//Calls Method LambdaOperations::deleteFunction()
//Deletes the lambda function
public class DeleteFunction
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing lambdaObject by calling another method
            //which returns reference of an object of class LambdaOperations
            LambdaOperations lambdaObject = getNewObject.newLambdaObject();

            String functionName = args[0];

            lambdaObject.deleteFunction(functionName);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
