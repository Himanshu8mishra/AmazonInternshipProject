package sqs;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Sends message to SQS queue and receives response from Lambda function
 * @author Himanshu Mishra
 */
public class HttpMessageClient
{
    private final SqsOperations sqsObject;
    private final String queueName;
    private final String message;
    private final String messageGroupID;
    private String IP;
    private int numberOfMessages;

    /**
     * @param sqsObject Object of class SqsOperations
     * @param queueName Name of the queue to send messages
     * @param message Original message that should be delivered
     * @param messageGroupID Group ID of the message to maintain sequence of messages
     * @param numberOfMessages Total messages that will be sent and received
     *                         Each of them will complete one cycle and
     *                         message count will be added at the end of each cycle
     */
    HttpMessageClient(SqsOperations sqsObject, String queueName, String message,
                      String messageGroupID, int numberOfMessages)
    {
        this.sqsObject = sqsObject;
        this.queueName = queueName;
        this.message = message;
        this.messageGroupID = messageGroupID;
        this.numberOfMessages = numberOfMessages;
    }

    private void setIPAddress() throws Exception
    {
        URL url_name = new URL("http://bot.whatismyipaddress.com");

        BufferedReader sc =
                new BufferedReader(new InputStreamReader(url_name.openStream()));

        this.IP = sc.readLine().trim();
    }

    private String getJsonMessage()
    {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("IP",this.IP);
        jsonObject.put("port","8080");
        jsonObject.put("message",this.message);
        jsonObject.put("count",1);

        return jsonObject.toJSONString();
    }

    /**
     * Sends messages to the SQS queue and
     * listens for response from Lambda function using socket server
     */
    public String messageProducer()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(8080);

            setIPAddress();
            String msg = getJsonMessage();

            //Sending initial message
            sqsObject.sendMessage(queueName, msg, messageGroupID);

            System.out.println("\nListening to Port: 8080");
            boolean flag = true;
            while (flag)
            {
                System.out.println("\n");
                Socket socket = serverSocket.accept();

                //Reading response from lambda function
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                while (true)
                {
                    line = in.readLine();
                    if(line.equals(""))
                        break;
                    System.out.println(line);
                }

                //Reading response body
                StringBuilder payload = new StringBuilder();
                while(in.ready())
                {
                    payload.append((char) in.read());
                }
                msg = payload.toString();
                System.out.println(msg);

                //Incrementing message count
                JSONObject jsonObject;
                JSONParser jsonParser = new JSONParser();
                jsonObject = (JSONObject) jsonParser.parse(msg);

                int oldValue = Integer.parseInt(jsonObject.get("count").toString());
                int newValue = oldValue + 1;
                if(newValue <= numberOfMessages)
                {
                    jsonObject.put("count", newValue);
                    msg = jsonObject.toJSONString();
                    sqsObject.sendMessage(queueName, msg, messageGroupID);
                }
                else
                    flag = false;

                //Sending OK response to lambda
                Date date = new Date();
                String response = "HTTP/1.1 200 OK\r\n\r\n" + date;
                socket.getOutputStream().write(response.getBytes(StandardCharsets.UTF_8));
                socket.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("\nError Occurred\n" + e);
        }
        return "Completed";
    }
}
