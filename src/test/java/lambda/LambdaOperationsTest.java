package lambda;

import org.junit.jupiter.api.*;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.DeleteEventSourceMappingRequest;
import software.amazon.awssdk.services.lambda.model.FunctionCode;
import static org.junit.jupiter.api.Assertions.*;

//Testing all the methods of class LambdaOperations
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LambdaOperationsTest
{
    private static final LambdaOperations unitTestObject = new LambdaOperations();
    private static final String functionName = "unitTestFunction";
    private static FunctionCode functionCode;
    private static LambdaClient lambdaClient;

    @BeforeAll
    public static void setUp()
    {
        lambdaClient = LambdaClient.builder().build();
        assertNotNull(lambdaClient);

        System.out.println("\nInitial Test Passed");
    }
    //Deleting function created during test
    //in case any test in between fails
    @AfterAll
    public  static  void tearDown()
    {
        try
        {
            unitTestObject.deleteFunction(functionName);
        }
        catch (Exception e)
        {
            System.out.println("Function doesn't exist");
        }
    }

    @Test
    @Order(1)
    public void getFunctionCode()
    {
        String bucketName = "ofabucket";
        String key = "SampleDeploy/DeployPackage-1.0-SNAPSHOT.jar";
        functionCode = unitTestObject.getFunctionCode(bucketName, key);
        assertNotNull(functionCode);

        System.out.println("\nTest 1");
    }

    @Test
    @Order(2)
    public void createFunction()
    {
        String handler = "example.Handler::handleRequest";
        String runtime = "java11";
        String role = "arn:aws:iam::161283720660:role/service-role/testRole";
        unitTestObject.createFunction(functionName, handler, runtime, role,functionCode);

        System.out.println("\nTest 2");
    }

    @Test
    @Order(3)
    public void invokeFunction()
    {
        String response = unitTestObject.invokeFunction(functionName);
        System.out.println(response);

        System.out.println("\nTest 3");
    }

    @Test
    @Order(4)
    public void addTrigger()
    {
        String sourceArn = "arn:aws:sqs:us-east-1:161283720660:testQueue";
        String uuid  = unitTestObject.addTrigger(functionName, sourceArn, false, 10);

        DeleteEventSourceMappingRequest deleteEventSourceMappingRequest =
                DeleteEventSourceMappingRequest.builder()
                        .uuid(uuid)
                        .build();
        lambdaClient.deleteEventSourceMapping(deleteEventSourceMappingRequest);

        System.out.println("\nTest 4");
    }

    @Test
    @Order(5)
    public void listFunctions()
    {
        unitTestObject.listFunctions();

        System.out.println("\nTest 5");
    }

    @Test
    @Order(6)
    public void deleteFunction()
    {
        unitTestObject.deleteFunction(functionName);

        System.out.println("\nTest 6");
    }
}