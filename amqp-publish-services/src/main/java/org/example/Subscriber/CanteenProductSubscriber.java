package org.example.Subscriber;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
public class CanteenProductSubscriber
{
    private final static String QUEUE_NAME = "viac.products.queue";
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // factory pattern for the connection
        ConnectionFactory factory = new ConnectionFactory();
        // set the host name
        factory.setHost("localhost");
        // create connection and initialize the channel
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // use the default consumer to control the deliveries
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, "UTF-8");
                System.out.println(" [Subscriber] Received '" + message + "'");
                // consume using: queue, bool noAck, callback
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}

