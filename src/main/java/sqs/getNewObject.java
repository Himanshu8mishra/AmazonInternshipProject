package sqs;

//This class contains methods that return objects
//of different classes like SQSOperations
public class getNewObject
{
    static SQSOperations sqsObject = null;

    //Returns object of SQSOperations during main processes
    //and returns mock object during testing
    //See class TestingMainSQS in test folder for more information
    static SQSOperations newSqsObject()
    {
        return (sqsObject != null ? sqsObject : new SQSOperations());
    }
}
