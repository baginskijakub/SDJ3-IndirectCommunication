package via.sdj3.rabbitmqspringbootexample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import via.sdj3.rabbitmqspringbootexample.publisher.SimplePublisher;

@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMQRestController {
    private SimplePublisher publisher;

    public RabbitMQRestController(SimplePublisher simplePublisher){
        this.publisher = simplePublisher;
    }

    @RequestMapping("/messages")
    public String sendMessage(@RequestParam("message") String message){
        System.out.println("Sending : " + message);
        publisher.send(message);
        return "Message sent to the RabbitMQ Broker";
    }

}
