package lambda;

//This class contains methods that return objects
//of different classes like LambdaOperations
public class getNewObject
{
    static LambdaOperations lambdaObject = null;

    //Returns object of LambdaOperations during main processes
    //and returns mock object during testing
    //See class TestingMainLambda in test folder for more information
    static LambdaOperations newLambdaObject()
    {
        return (lambdaObject !=null ? lambdaObject : new LambdaOperations() );
    }
}
