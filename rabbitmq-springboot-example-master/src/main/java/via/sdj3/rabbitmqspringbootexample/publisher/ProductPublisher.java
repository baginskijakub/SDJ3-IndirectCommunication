package via.sdj3.rabbitmqspringbootexample.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import via.sdj3.rabbitmqspringbootexample.model.Product;

@Service
public class ProductPublisher {
    private RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPublisher.class);
    private String EXCHANGE = "products.exchange";
    private String ROUTING_KEY = "product.routingkey";

    public ProductPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean send(Product p)
    {
        try{
            LOGGER.info(String.format("Message sent ===> %s", p.toString()));
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, p);
            return true;
        }
        catch (Exception e){
            LOGGER.error(String.format("Error ===> %s", e.toString()));
            return false;
        }
    }
}
