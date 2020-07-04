package lambda;

import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.CreateEventSourceMappingRequest;
import software.amazon.awssdk.services.lambda.model.CreateEventSourceMappingResponse;
import software.amazon.awssdk.services.lambda.model.CreateFunctionRequest;
import software.amazon.awssdk.services.lambda.model.CreateFunctionResponse;
import software.amazon.awssdk.services.lambda.model.DeleteFunctionRequest;
import software.amazon.awssdk.services.lambda.model.FunctionCode;

/**
 * Contains methods for all the basic operations related to AWS Lambda
 * @author Himanshu Mishra
 */
public class LambdaOperations
{
    private static LambdaClient lambdaClient;

    LambdaOperations()
    {
        lambdaClient = LambdaClient.builder().build();
    }

    /**
     * Creates a reference for an object with details regarding
     * deployment package to be uploaded in lambda function
     * @param bucketName Name of the bucket which stores your deployment package
     * @param key S3 key of your package file
     * @return Returns a reference of your deployment package for lambda function stored in s3 bucket
     */
    public FunctionCode getFunctionCode(String bucketName, String key)
    {
        return FunctionCode.builder()
                .s3Bucket(bucketName)
                .s3Key(key)
                .build();
    }

    /**
     * Creates a lambda function in AWS
     * @param functionName Name of the function to be created
     * @param handler Name of the method that will be invoked by lambda
     *               (Syntax - package.className::methodName)
     * @param runtime Environment on which your package runs (like java11)
     * @param roleArn ARN of the IAM role to be attached to your function
     * @param functionCode Reference of package stored in s3 bucket
     * @return Name of the function created
     */
    public String createFunction(String functionName, String handler, String runtime,
                                      String roleArn, FunctionCode functionCode)
    {
        System.out.println("\nCreating Function: " + functionName);

        CreateFunctionRequest createFunctionRequest = CreateFunctionRequest.builder()
                .functionName(functionName)
                .handler(handler)
                .runtime(runtime)
                .role(roleArn)
                .code(functionCode)
                .build();
        CreateFunctionResponse createFunctionResponse = lambdaClient.createFunction(createFunctionRequest);

        return createFunctionResponse.functionName();
    }

    /**
     * Adds event source to the specified function
     * @param functionName Name of the function
     * @param sourceArn ARN of trigger
     * @param enableTrigger To enable the trigger straight away or not (you can enable it later as well)
     * @param messagePollingLimit Max messages that can be polled in one go
     * @return UUID of the event source mapping
     */
    public String addTrigger(String functionName, String sourceArn, boolean enableTrigger, int messagePollingLimit)
    {
        System.out.println("\nAdding Trigger");

        CreateEventSourceMappingRequest createEventSourceMappingRequest =
                CreateEventSourceMappingRequest.builder()
                        .functionName(functionName)
                        .eventSourceArn(sourceArn)
                        .enabled(enableTrigger)
                        .batchSize(messagePollingLimit)
                        .build();

        CreateEventSourceMappingResponse createEventSourceMappingResponse =
                lambdaClient.createEventSourceMapping(createEventSourceMappingRequest);

        System.out.println("Trigger Added");
        return (createEventSourceMappingResponse.uuid());
    }

    /**
     * Deletes the specified function
     * @param functionName Name of the function to be deleted
     */
    public void deleteFunction(String functionName)
    {
        System.out.println("\nDeleting Function: " + functionName);

        DeleteFunctionRequest deleteFunctionRequest = DeleteFunctionRequest.builder()
                .functionName(functionName)
                .build();
        lambdaClient.deleteFunction(deleteFunctionRequest);

        System.out.println(functionName + " deleted");
    }
}
