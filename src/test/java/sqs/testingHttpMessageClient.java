package sqs;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testing methods of class HttpMessageClient
 * Since HttpMessageClient::messageProducer is expecting a response
 * on port 8080, you have to use some external application to complete
 * this test by sending a Http request on http://localhost:8080/
 * Request body must be in json format and
 * should contain 'count' key with any integer as its value
 * @author Himanshu Mishra
 */
class testingHttpMessageClient
{
    @Test
    void testingMessageProducer()
    {
        String queueName = "queue.fifo";
        String message = "testMessage";
        String messageGroupID = "testGroup";

        SqsOperations sqsObject = mock(SqsOperations.class);
        when(sqsObject.sendMessage(queueName, message, messageGroupID)).thenReturn("testUrl");

        HttpMessageClient httpMessageClient =
                new HttpMessageClient(sqsObject, queueName, message, messageGroupID,1);
        httpMessageClient.messageProducer();
    }
}