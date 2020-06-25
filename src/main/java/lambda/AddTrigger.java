package lambda;

//Calls Method LambdaOperations::addTrigger()
//Specifies event source for the lambda function
public class AddTrigger
{
    public static void main(String[] args)
    {
        try
        {
            //Initializing lambdaObject by calling another function
            //which returns reference of an object of class LambdaOperations
            LambdaOperations lambdaObject = getNewObject.newLambdaObject();

            String functionName = args[0];
            String sourceArn = args[1];
            boolean enableTrigger = Boolean.parseBoolean(args[2]);
            int batchSize = Integer.parseInt(args[3]);
            
            String uuid = lambdaObject.addTrigger(functionName,sourceArn,enableTrigger,batchSize);
            System.out.println(uuid);
        }
        catch(Exception e)
        {
            System.out.println("\nError Occurred\n"+e);
        }
    }
}
