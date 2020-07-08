# AmazonInternshipProject

## Project Description
AWS Lambda service (not your Lambda function but a Lambda SQS long-poll service running on your behalf) polls your SQS queue continually for incoming messages. When a message arrives, it receives the message(s) and then invokes your Lambda function by passing the message(s) as a parameter.

With Lambda as a target for SQS event source, it fully supports reading and deleting messages in batch (1 to 10) within a queue. In addition to configurable batch support, Lambda function scaling starts polling your queue with a (minimum) concurrency of 5 invocations and will increase concurrency by 60 concurrent invocations per minute as needed. You can manually control the Lambda function concurrency from 1 to 1000, but Lambda will always default to an initial burst of 5 concurrent function invocations. This is where all the heavy lifting comes in, allowing you to process thousands of requests per second with only a single standard queue.

### Files Description

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
3. Build your project using following command ```mvn package```. For more details [click here](https://maven.apache.org/users/index.html "click here")
4. Make sure to update your aws credential files.  [Click Here](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-files.html "Click Here") You can do that from your editor as well if you have installed AWS toolkit for your editor.
5. To run these programs on your EC2 server transfer your package created by maven to your EC2 instance.
6. Go into package directory where you will have an executable .jar file which contains all class files.
7. Make sure you transferred entire package to your EC2 instance because it conatins .jar files of your dependencies as well.
8. After you have installed java in your EC2 instance run programs using following command: ``` java -cp <jar_filename.jar> <package.class_name> <command_line_arguments>```
