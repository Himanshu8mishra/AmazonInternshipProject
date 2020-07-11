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

=======
- **Lambda Package**

**LambdaOperations.java -** Contains method performing basic operations related to SQS
```java
public FunctionCode getFunctionCode(String bucketName, String key)
public void createFunction(String functionName, String handler, String runtime, String role, FunctionCode functionCode)
public String addTrigger(String functionName, String sourceArn, boolean enableTrigger, int messagePollingLimit)
public void deleteFunction(String functionName)
```

**CreateFunction.java -** calls method *LambdaOperations::createFunction()*

It takes following variables as command line arguments: ```functionName```  
Takes following input from config file: ```handler, runtime, role, bucketName, s3key```

**AddTrigger.java -** calls method *LambdaOperations::addTrigger()*

It takes following variables as command line arguments: ```functionName```  
Takes following input from config file: ```sourceArn, enableTrigger, batchSize```

**DeleteFunction.java -** calls method *LambdaOperations::receiveMessage()*

It takes following variables as command line arguments: ```functionName```

## Setup

1. Clone or download project files in your local machine.
2. Open your project in your preferred editor (recommended Intellij) as a maven project. You can search online for exact procedure according to your editor.
3. Open command prompt and go to directory of your project.
4. Build your project using following command ```mvn package```. For more details [click here](https://maven.apache.org/users/index.html "click here")
5. Make sure to update your aws credential files.  [Click Here](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-files.html "Click Here") You can do that from your editor as well if you have installed AWS toolkit for your editor.
6. Connect to your EC2 instance and make sure you attach proper IAM role to your EC2 instance with all the required policies.
6. To run these programs on your EC2 server transfer your package folder created by maven to your EC2 instance.
7. Go into package directory where you will have an executable .jar file which contains all class files.
8. Make sure you have transferred entire package to your EC2 instance because it conatins .jar files of your dependencies as well.
9. After you have installed java in your EC2 instance run programs using following command: ``` java -cp <jar_filename.jar> <package.class_name> <command_line_arguments>```

##Working

####1. Creating Fifo queue:
```java -cp amznProject-1.0.jar sqs.CreateFifoQueue FifoQueue.fifo```

####2. Creating Lambda Function
**Note:**  
You have to upload .jar file containing code for your lambda function in a S3 bucket.  
You can find the required jar file in src/main/resources directory (DeployPackage-1.0-SNAPSHOT.jar).  
Update LambdaConfig.properties file in src/main/resources directory with ARN values of your resources.
     
```java -cp amznProject-1.0.jar lambda.CreateFunction testFunction```

####3. Adding Fifo queue as an event trigger
**Note:** Update your LambdaConfig.properties files according to your resources ARN values  
```java -cp amznProject-1.0.jar lambda.AddTrigger testFunction```

####Run your setup:
```java -cp amznProject-1.0.jar sqs.InitiateHttpClient FifoQueue.fifo testMessage group1```

By default 1000 messages will be sent to the queue but you can change it in SqsConfig.properties file.
