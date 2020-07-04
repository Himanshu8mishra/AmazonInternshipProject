package lambda;

/**
 * Deletes the lambda function
 * @author Himanshu Mishra
 */
public class DeleteFunction
{
    /**
     * Calls Method LambdaOperations::deleteFunction()
     * @param args
     * Required variables:
     * functionName - Name of the function to be deleted
     */
    public static void main(String[] args)
    {
        try
        {
            LambdaOperations lambdaObject = new LambdaOperations();

            String functionName = args[0];

            lambdaObject.deleteFunction(functionName);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
    }
}
