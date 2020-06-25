package sqs;

//Mock class of SQSOperations
public class mockSQSOperations extends SQSOperations
{
    @Override
    public void createStandardQueue(String queueName)
    {
        System.out.println("Inside createStandardQueue");
    }
    @Override
    public void listQueues()
    {
        System.out.println("Inside listQueues");
    }
    @Override
    public void configureQueue(String queueName, String visibilityTimeout, String pollTime)
    {
        System.out.println("Inside configureQueue");
    }
    @Override
    public void sendMessage(String queueName, String message)
    {
        System.out.println("Inside sendMessage");
    }
    @Override
    public void receiveMessage(String queueName, int maxMessages, int waitTime)
    {
        System.out.println("Inside receiveMessage");
    }
    @Override
    public void purgeQueue(String queueName)
    {
        System.out.println("Inside purgeQueue");
    }
    @Override
    public void deleteQueue(String queueName)
    {
        System.out.println("Inside deleteQueue");
    }
}
