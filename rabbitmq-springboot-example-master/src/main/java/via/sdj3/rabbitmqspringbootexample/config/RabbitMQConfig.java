package via.sdj3.rabbitmqspringbootexample.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    ///rabbitmq direct exchange spring bean

    @Bean
    public Exchange productExchange(){
        return new DirectExchange("products.exchange");
    }

    @Bean
    public Queue productQueue(){
        return new Queue("product.queue");
    }

    @Bean
    public Queue messegeQueue() {
        return new Queue("distribution.notification");
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(productQueue())
                .to(productExchange())
                .with("product.routingkey")
                .noargs();
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(messegeQueue())
                .to(productExchange())
                .with("distribution.notification.routingkey")
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}
