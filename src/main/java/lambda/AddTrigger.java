package lambda;

//Calls Method LambdaExample::addTrigger()
//Specifies event source for the lambda function
public class AddTrigger
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing testObject by calling another function
            //which returns reference of an object of class LambdaExample
            LambdaExample testObject = getNewLambdaExample.newLambdaExample();

            String functionName = args[0];
            String sourceArn = args[1];
            boolean enableTrigger = Boolean.parseBoolean(args[2]);
            int batchSize = Integer.parseInt(args[3]);
            
            testObject.addTrigger(functionName,sourceArn,enableTrigger,batchSize);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
