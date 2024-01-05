package com.example.orderservice.Controller;

import com.example.basedomains.Dto.Order;
import com.example.basedomains.Dto.OrderEvent;
import com.example.orderservice.Kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();

        orderEvent.setStatus("PENDING...!");
        orderEvent.setMessage("Order is in pending....!");
        orderEvent.setOrder(order);

        orderProducer.sendMesage(orderEvent);

        return "Order Placed Successfully...!";
    }
}
