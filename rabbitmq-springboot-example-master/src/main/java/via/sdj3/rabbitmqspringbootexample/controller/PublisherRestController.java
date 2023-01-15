package via.sdj3.rabbitmqspringbootexample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.rabbitmqspringbootexample.model.Product;
import via.sdj3.rabbitmqspringbootexample.publisher.ProductPublisher;

@RestController
@RequestMapping("/api/v1")
public class PublisherRestController {
    private ProductPublisher productPublisher;

    public PublisherRestController(ProductPublisher productPublisher){
        this.productPublisher = productPublisher;
    }

    @PostMapping("/products")
    public ResponseEntity<String> publishProductDetails(@RequestBody Product product){
        if(productPublisher.send(product)){
            System.out.println(product.toString());
            return ResponseEntity.ok(product.toString() + "sent to the Broker");
        }
        else{
            return ResponseEntity.status(500).body("Error");
        }
    }
}
