package lambda;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestingMainLambda extends getNewObject
{
    @BeforeAll
    public static void setUp()
    {
        System.out.println("\nInitial Test");
        lambdaObject = new LambdaOperationsMock();
    }
    @AfterAll
    public static void tearDown()
    {
        System.out.println("\nFinal Test");
        lambdaObject = null;
    }
    @Test
    public void testingAddTrigger()
    {
        System.out.println("\nTest 1");
        String[] args = {"dummyFunction","dummySourceArn","true","10"};
        AddTrigger.main(args);
    }
    @Test
    public void testingCreateFunction()
    {
        System.out.println("\nTest 2");
        String[] args = {"dummyFunction","dummyHandler","dummyRuntime","dummyRole","dummyBucket","dummyKey"};
        CreateFunction.main(args);
    }
    @Test
    public void testingDeleteFunction()
    {
        System.out.println("\nTest 3");
        String[] args = {"dummyFunction"};
        DeleteFunction.main(args);
    }
    @Test
    public void testingInvokeFunction()
    {
        System.out.println("\nTest 4");
        String[] args = {"dummyFunction"};
        InvokeFunction.main(args);
    }
    @Test
    public void testingListLambdaFunctions()
    {
        System.out.println("\nTest 5");
        String[] args = {};
        ListLambdaFunctions.main(args);
    }
}
