package lambda;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.*;
import java.util.*;

//Contains methods for all the basic operations related to AWS Lambda
public class LambdaOperations
{
    private static LambdaClient lambdaClient;

    LambdaOperations()
    {
        lambdaClient = LambdaClient.builder().build();
    }

    //Returns a reference of your deployment package stored in s3 bucket
    public FunctionCode getFunctionCode(String bucketName, String key)
    {
        return FunctionCode.builder()
                .s3Bucket(bucketName)
                .s3Key(key)
                .build();
    }

    public void createFunction(String functionName, String handler, String runtime,
                                      String role, FunctionCode functionCode)
    {
        System.out.println("\nCreating Function: "+functionName);

        CreateFunctionRequest createFunctionRequest = CreateFunctionRequest.builder()
                .functionName(functionName)
                .handler(handler)
                .runtime(runtime)
                .role(role)
                .code(functionCode)
                .build();
        lambdaClient.createFunction(createFunctionRequest);

        System.out.println("Function Created");
    }

    public String invokeFunction(String functionName)
    {
        System.out.println("\nInvoking Function: "+functionName);

        SdkBytes payload = SdkBytes.fromUtf8String
            ("{\n"+
            "\"Records\": [\n"+
            "{\n"+
            "\"messageId\": \"19dd0b57-b21e-4ac1-bd88-01bbb068cb78\",\n"+
            "\"receiptHandle\": \"MessageReceiptHandle\",\n"+
            "\"body\": \"{ \\\"IP\\\":\\\"34.207.64.196\\\",\\\"port\\\":\\\"80\\\",\\\"message\\\":\\\"testMessage\\\",\\\"count\\\":\\\"99\\\"}\",\n"+
            "\"attributes\": {\n"+
            "\"ApproximateReceiveCount\": \"1\",\n"+
            "\"SentTimestamp\": \"1523232000000\",\n"+
            "\"SenderId\": \"123456789012\",\n"+
            "\"ApproximateFirstReceiveTimestamp\": \"1523232000001\"\n"+
            "},\n"+
            "\"messageAttributes\": {},\n"+
            "\"md5OfBody\": \"7b270e59b47ff90a553787216d55d91d\",\n"+ "\"eventSource\": \"aws:sqs\",\n"+
            "\"eventSourceARN\": \"arn:aws:sqs:us-west-2:123456789012:MyQueue\",\n"+
            "\"awsRegion\": \"us-west-1\"\n"+
            "}\n"+
            "]\n"+
            "}");

        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(functionName)
                .payload(payload)
                .build();

        InvokeResponse response = lambdaClient.invoke(invokeRequest);

        return (response.payload().asUtf8String());
    }

    public String addTrigger(String functionName, String sourceArn, boolean enableTrigger, int batchSize)
    {
        System.out.println("\nAdding Trigger");

        CreateEventSourceMappingRequest createEventSourceMappingRequest =
                CreateEventSourceMappingRequest.builder()
                        .functionName(functionName)
                        .eventSourceArn(sourceArn)
                        .enabled(enableTrigger)
                        .batchSize(batchSize)
                        .build();

        CreateEventSourceMappingResponse createEventSourceMappingResponse =
                lambdaClient.createEventSourceMapping(createEventSourceMappingRequest);

        System.out.println("Trigger Added");
        return (createEventSourceMappingResponse.uuid());
    }

    public void listFunctions()
    {
        System.out.println("\nFunction List:");

        ListFunctionsResponse listFunctionsResponse = lambdaClient.listFunctions();

        List<FunctionConfiguration> list = listFunctionsResponse.functions();

        for (FunctionConfiguration iterator : list)
        {
            System.out.println(iterator.functionName());
        }
    }

    public void deleteFunction(String functionName)
    {
        System.out.println("\nDeleting Function: "+functionName);

        DeleteFunctionRequest deleteFunctionRequest = DeleteFunctionRequest.builder()
                .functionName(functionName)
                .build();
        lambdaClient.deleteFunction(deleteFunctionRequest);

        System.out.println("Function Deleted");
    }
}
