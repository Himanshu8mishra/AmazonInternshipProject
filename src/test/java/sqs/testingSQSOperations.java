package sqs;

import org.junit.jupiter.api.*;
import software.amazon.awssdk.services.sqs.SqsClient;

import static org.junit.jupiter.api.Assertions.*;

//Testing all the methods of class SQSOperations
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class testingSQSOperations
{
    private static final SQSOperations unitTestObject = new SQSOperations();
    private static final String queueName = "unitTestQueue";

    @BeforeAll
    public static void setUp()
    {
        SqsClient sqsClient = SqsClient.builder().build();
        assertNotNull(sqsClient);

        System.out.println("\nInitial Test");
    }
    //Deleting queue created during test
    //in case any test in between fails
    @AfterAll
    public  static  void tearDown()
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
    public void createStandardQueue()
    {
        unitTestObject.createStandardQueue(queueName);

        System.out.println("\nTest 1");
    }

    @Test
    @Order(2)
    public void listQueues()
    {
        unitTestObject.listQueues();

        System.out.println("\nTest 2");
    }

    @Test
    @Order(3)
    public void configureQueue()
    {
        String visibilityTimeout = "60";
        String pollTime = "20";
        unitTestObject.configureQueue(queueName, visibilityTimeout, pollTime);

        System.out.println("\nTest 3");
    }

    @Test
    @Order(4)
    public void sendMessage()
    {
        String message = "unitTestMessage";
        unitTestObject.sendMessage(queueName, message);

        System.out.println("\nTest 4");
    }

    @Test
    @Order(5)
    public void receiveMessage()
    {
        int maxMessages = 10;
        int waitTime = 1;
        unitTestObject.receiveMessage(queueName, maxMessages, waitTime);

        System.out.println("\nTest 5");
    }

    @Test
    @Order(6)
    public void purgeQueue()
    {
        unitTestObject.purgeQueue(queueName);

        System.out.println("\nTest 6");
    }

    @Test
    @Order(7)
    public void deleteQueue()
    {
        unitTestObject.deleteQueue(queueName);

        System.out.println("\nTest 7");
    }
}