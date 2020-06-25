package lambda;

import software.amazon.awssdk.services.lambda.model.*;

//Mock class of lambda example
public class LambdaOperationsMock extends LambdaOperations
{
    @Override
    public FunctionCode getFunctionCode(String bucketName, String key)
    {
        System.out.println("Inside getFunctionCode");
        return null;
    }
    @Override
    public void createFunction(String functionName, String handler, String runtime,
                               String role, FunctionCode functionCode)
    {
        System.out.println("Inside createFunction");
    }
    @Override
    public String invokeFunction(String functionName)
    {
        System.out.println("Inside invokeFunction");
        return "dummyResponse";
    }
    @Override
    public String addTrigger(String functionName, String sourceArn, boolean enableTrigger, int batchSize)
    {
        System.out.println("Inside addTrigger");
        return "dummyUUID";
    }
    @Override
    public void listFunctions()
    {
        System.out.println("Inside listFunctions");
    }
    @Override
    public void deleteFunction(String functionName)
    {
        System.out.println("Inside deleteFunction");
    }
}
