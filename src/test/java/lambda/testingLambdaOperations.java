package lambda;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.DeleteEventSourceMappingRequest;
import software.amazon.awssdk.services.lambda.model.EventSourceMappingConfiguration;
import software.amazon.awssdk.services.lambda.model.FunctionCode;
import software.amazon.awssdk.services.lambda.model.FunctionConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing methods of class LambdaOperations
 * @author Himanshu Mishra
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class testingLambdaOperations
{
    private static final LambdaOperations unitTestObject = new LambdaOperations();
    private static String functionName;
    private static String bucketName;
    private static String key;
    private static String handler;
    private static String runtime;
    private static String role;
    private static String sourceArn;
    private static FunctionCode functionCode;
    private static LambdaClient lambdaClient;
    private static String uuid;

    @BeforeAll
    public static void setUp() throws IOException
    {
        lambdaClient = LambdaClient.builder().build();
        assertNotNull(lambdaClient);

        InputStream input =
                CreateFunction.class.getClassLoader().getResourceAsStream("LambdaConfig.properties");

        Properties prop = new Properties();
        prop.load(input);

        functionName = prop.getProperty("functionName");
        bucketName = prop.getProperty("bucketName");
        key = prop.getProperty("s3key");
        handler = prop.getProperty("handler");
        runtime = prop.getProperty("runtime");
        role = prop.getProperty("roleArn");
        sourceArn = prop.getProperty("sourceArn");
    }
    @AfterAll
    public  static  void tearDown()
    {
        DeleteEventSourceMappingRequest deleteEventSourceMappingRequest =
                DeleteEventSourceMappingRequest.builder()
                        .uuid(uuid)
                        .build();
        lambdaClient.deleteEventSourceMapping(deleteEventSourceMappingRequest);
    }

    @Test
    @Order(1)
    public void testingGetFunctionCode()
    {
        functionCode = unitTestObject.getFunctionCode(bucketName, key);
        assertNotNull(functionCode);
    }

    @Test
    @Order(2)
    public void testingCreateFunction()
    {
        String response = unitTestObject.createFunction(functionName, handler, runtime, role,functionCode);

        List<FunctionConfiguration> functionConfigurations = lambdaClient.listFunctions().functions();
        boolean flag = false;
        for(FunctionConfiguration iterator : functionConfigurations)
        {
            if(functionName.equals(iterator.functionName()))
            {
                flag = true;    break;
            }
        }
        assertTrue(flag);
        assertEquals(functionName,response);
    }

    @Test
    @Order(3)
    public void testingAddTrigger()
    {
        uuid  = unitTestObject.addTrigger(functionName, sourceArn, false, 10);

        List<EventSourceMappingConfiguration> list =
                lambdaClient.listEventSourceMappings().eventSourceMappings();
        boolean flag = false;
        for(EventSourceMappingConfiguration iterator : list)
        {
            if(uuid.equals(iterator.uuid()))
            {
                flag = true;    break;
            }
        }
        assertTrue(flag);
    }

    @Test
    @Order(4)
    public void testingDeleteFunction()
    {
        unitTestObject.deleteFunction(functionName);

        List<FunctionConfiguration> functionConfigurations = lambdaClient.listFunctions().functions();
        boolean flag = false;
        for(FunctionConfiguration iterator : functionConfigurations)
        {
            if(functionName.equals(iterator.functionName()))
            {
                flag = true;    break;
            }
        }
        assertFalse(flag);
    }
}