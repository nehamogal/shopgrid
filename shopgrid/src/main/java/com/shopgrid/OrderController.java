package com.shopgrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderProducer producer;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent event) {
        producer.sendOrder(event);
        return ResponseEntity.ok("Order queued to Kafka successfully");
    }
}