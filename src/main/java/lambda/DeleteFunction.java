package lambda;

/**
 * Calls Method LambdaOperations::deleteFunction()
 * Deletes the lambda function
 * @author Himanshu Mishra
 */
public class DeleteFunction
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

            String response = lambdaObject.deleteFunction(functionName);
            System.out.println(response + "\n" + functionName + " deleted");
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
