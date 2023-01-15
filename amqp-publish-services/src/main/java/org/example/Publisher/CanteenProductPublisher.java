package org.example.Publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class CanteenProductPublisher {
    private final static String QUEUE_NAME = "viac.products.queue";
    public static void main(String[] args) throws Exception
    {
        String[] viaProducts = {"Cappuccino Cafe Latte", "Deep fried Hawai Vegan Pizza"};
        // factory pattern to init connection
        ConnectionFactory factory = new ConnectionFactory();
        // set the hostname.
        factory.setHost("localhost");
        // create the connection using the factory instance
        // and initialize the channel using connection
        // channel is responsible for sending, receiving, plus some queue operations
        try (Connection conn = factory.newConnection(); Channel channel = conn.createChannel()) {
            // with channel, we can declare, bind, unbind, del queue, ...// declare the queue// queue name, passive?, exclusive?, autoDel? any arguments
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // publish message directly to the provided queue
            for (String vp : viaProducts) {
                String message = "VIA Canteen's new product: " + vp;
                //  exchange, routingKey, AMQP.properties, byte[] body of msg
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [Pub] Sent '" + message + "'");
            }
        }
    }
}

