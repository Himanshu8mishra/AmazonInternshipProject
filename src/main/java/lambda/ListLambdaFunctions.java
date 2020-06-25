package lambda;

//Calls Method LambdaOperations::listFunctions()
//Prints the list of functions
public class ListLambdaFunctions
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing lambdaObject by calling another function
            //which returns reference of an object of class LambdaOperations
            LambdaOperations lambdaObject = getNewObject.newLambdaObject();

            lambdaObject.listFunctions();
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
