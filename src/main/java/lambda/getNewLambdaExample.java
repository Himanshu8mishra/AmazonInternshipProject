package lambda;

//This class contains methods that return objects
//of different classes like LambdaExample
public class getNewLambdaExample
{
    static LambdaExample instanceLambdaExample = null;

    //Returns object of LambdaExample during main processes
    //and returns mock object during testing
    //See class TestingMainLambda in test folder for more information
    static LambdaExample newLambdaExample()
    {
        return (instanceLambdaExample !=null ? instanceLambdaExample : new LambdaExample() );
    }
}
