package sqs;

//This class contains methods that return objects
//of different classes like SQSExample and Producer
public class getNewSQSExample
{
    static SQSExample instanceSQSExample = null;

    //Returns object of SQSExample during main processes
    //and returns mock object during testing
    //See class TestingMainSQS in test folder for more information
    static SQSExample newSQSExample()
    {
        return (instanceSQSExample != null ? instanceSQSExample : new SQSExample());
    }
}
