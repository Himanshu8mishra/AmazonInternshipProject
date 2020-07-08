# AmazonInternshipProject

## Project Description
AWS Lambda service (not your Lambda function but a Lambda SQS long-poll service running on your behalf) polls your SQS queue continually for incoming messages. When a message arrives, it receives the message(s) and then invokes your Lambda function by passing the message(s) as a parameter.

With Lambda as a target for SQS event source, it fully supports reading and deleting messages in batch (1 to 10) within a queue. In addition to configurable batch support, Lambda function scaling starts polling your queue with a (minimum) concurrency of 5 invocations and will increase concurrency by 60 concurrent invocations per minute as needed. You can manually control the Lambda function concurrency from 1 to 1000, but Lambda will always default to an initial burst of 5 concurrent function invocations. This is where all the heavy lifting comes in, allowing you to process thousands of requests per second with only a single standard queue.

### Files Description

- **SQS Package**

**SqsOperations.java -** Contains method performing basic operations related to SQS
```java
public void createFifoQueue(String queueName)
public void configureQueue(String queueName, String visibilityTimeout, String pollTime)
public void sendMessage(String queueName, String message, String messageGroupID)
public void deleteQueue(String queueName)
```

**CreateFifoQueue.java -** calls method *SqsOperations::createFifoQueue()*

It takes following variables as command line arguments: ```queueName```

**ConfigureQueue.java -** calls method *SqsOperations::configureQueue()*

It takes following variables as command line arguments: ```queueName, visibilityTimeout, pollTime```

**DeleteQueue.java -** calls method *SqsOperations::deleteQueue()*

It takes following variables as command line arguments: ```queueName```

**HttpMessageClient.java -** Contains method which produces messages and send it to AWS SQS and listens for response from AWS Lambda on a specific port and increases message count after each cycle.  
By default number of cycles/messages are set to 1000 in config file.
 
```
Constructor:
HttpMessageClient(SqsOperations sqsObject, String queueName, String message, String messageGroupID, int numberOfMessages)

public void messageProducer()
```

**InitiateHttpClient.java -** calls method *HttpMessageClient::messageProducer()*

It takes following variables as command line arguments: ```queueName, message, messageGroupID```
Takes following input from config file: ```numberOfMessages```

