package sqs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestingMainSQS extends getNewSQSExample
{
    @BeforeAll
    public static void setUp()
    {
        System.out.println("\nInitial Test");
        instanceSQSExample = new SQSExampleMock();
    }
    @AfterAll
    public static void tearDown()
    {
        System.out.println("\nFinal Test");
        instanceSQSExample = null;
    }
    @Test
    public void testingCreateStandardQueue()
    {
        System.out.println("\nTest 1");
        String[] args = {"dummyQueue"};
        Create_Standard_Queue.main(args);
    }
    @Test
    public void testingListQueues()
    {
        System.out.println("\nTest 2");
        String[] args = {};
        List_Queues.main(args);
    }
    @Test
    public void testingConfigureQueue()
    {
        System.out.println("\nTest 3");
        String[] args = {"dummyQueue","60","10"};
        Configure_Queue.main(args);
    }
    @Test
    public void testingSendMessage()
    {
        System.out.println("\nTest 4");
        String[] args = {"dummyQueue","dummyMessage"};
        Send_Message.main(args);
    }
    @Test
    public void testingReceiveMessage()
    {
        System.out.println("\nTest 5");
        String[] args = {"dummyQueue","10","5"};
        Receive_Messages.main(args);
    }
    @Test
    public void testingPurgeQueue()
    {
        System.out.println("\nTest 6");
        String[] args = {"dummyQueue"};
        Purge_Queue.main(args);
    }
    @Test
    public void testingDeleteQueue()
    {
        System.out.println("\nTest 7");
        String[] args = {"dummyQueue"};
        Delete_Queue.main(args);
    }
}
