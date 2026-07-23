package com.threefour.backend.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/rav")
    public String hello() {
        return "Hello Ruchitha";
    }

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody Order order) {
        Order savedOrder = orderService.saveRuchitha(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {

        Order order = orderService.getOrderById((int) id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {

        orderService.deleteOrder(id);

        return ResponseEntity.ok("Order deleted successfully.");
    }




}