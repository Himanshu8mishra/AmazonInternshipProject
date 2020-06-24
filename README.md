# AmazonInternshipProject

## Project Description
AWS Lambda service (not your Lambda function but a Lambda SQS long-poll service running on your behalf) polls your SQS queue continually for incoming messages. When a message arrives, it receives the message(s) and then invokes your Lambda function by passing the message(s) as a parameter.

With Lambda as a target for SQS event source, it fully supports reading and deleting messages in batch (1 to 10) within a queue. In addition to configurable batch support, Lambda function scaling starts polling your queue with a (minimum) concurrency of 5 invocations and will increase concurrency by 60 concurrent invocations per minute as needed. You can manually control the Lambda function concurrency from 1 to 1000, but Lambda will always default to an initial burst of 5 concurrent function invocations. This is where all the heavy lifting comes in, allowing you to process thousands of requests per second with only a single standard queue.

### Files Description

- **SQS Package**

**SQSExample.java -** Contains method performing basic operations related to SQS
```java
public void createStandardQueue(String queueName)
public void listQueues()
public void configureQueue(String queueName, String visibilityTimeout, String pollTime)
public void sendMessage(String queueName, String message)
public void receiveMessage(String queueName, int maxMessages, int waitTime)
public void purgeQueue(String queueName)
public void deleteQueue(String queueName)
```

**Create_Standard_Queue.java -** calls method *SQSExample::createStandardQueue()*

It takes following variables as command line arguments: ```queueName```

**List_Queues.java -** calls method *SQSExample::listQueues()*

**Configure_Queue.java -** calls method *SQSExample::configureQueue()*

It takes following variables as command line arguments: ```queueName, visibilityTimeout, pollTime```

**Send_Message.java -** calls method *SQSExample::sendMessage()*

It takes following variables as command line arguments: ```queueName, message```

**Receive_Messages.java -** calls method *SQSExample::receiveMessage()*

It takes following variables as command line arguments: ```queueName, maxMessages, waitTime```

**Purge_Queue.java -** calls method *SQSExample::purgeQueue()*

It takes following variables as command line arguments: ```queueName```

**Delete_Queue.java -** calls method *SQSExample::deleteQueue()*

It takes following variables as command line arguments: ```queueName```