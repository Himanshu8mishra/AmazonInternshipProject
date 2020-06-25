package lambda;

//Calls Method LambdaExample::listFunctions()
//Prints the list of functions
public class ListLambdaFunctions
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another function
            //which returns reference of an object of class LambdaExample
            LambdaExample testObject = getNewLambdaExample.newLambdaExample();

            testObject.listFunctions();
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
