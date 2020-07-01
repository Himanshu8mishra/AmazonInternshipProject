package sqs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import software.amazon.awssdk.services.sqs.SqsClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing all the methods of class SqsOperations
 * @author Himanshu Mishra
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class testingSqsOperations
{
    private static final SqsOperations unitTestObject = new SqsOperations();
    private static final String queueName = "unitTestQueue.fifo";

    @BeforeAll
    public static void setUp()
    {
        SqsClient sqsClient = SqsClient.builder().build();
        assertNotNull(sqsClient);
    }
    @AfterAll
    public static void tearDown()
    {
        try
        {
            unitTestObject.deleteQueue(queueName);
        }
        catch (Exception e)
        {
            System.out.println("Queue doesn't exist");
        }
    }

    @Test
    @Order(1)
    public void testingCreateFifoQueue()
    {
        String response = unitTestObject.createFifoQueue(queueName);
        assertNotNull(response);
    }

    @Test
    @Order(2)
    public void testingConfigureQueue()
    {
        String visibilityTimeout = "60";
        String pollTime = "20";
        String response = unitTestObject.configureQueue(queueName, visibilityTimeout, pollTime);
        assertNotNull(response);
    }

    @Test
    @Order(3)
    public void testingDeleteQueue()
    {
        String response = unitTestObject.deleteQueue(queueName);
        assertNotNull(response);
    }
}