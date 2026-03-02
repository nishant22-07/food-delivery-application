package com.nishant.foodiesapi.controller;

import com.nishant.foodiesapi.IO.OrderRequest;
import com.nishant.foodiesapi.IO.OrderResponse;
import com.nishant.foodiesapi.service.OrderService;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrderWithPayment(@RequestBody OrderRequest request) throws RazorpayException {
        OrderResponse response = orderService.createOrderWithPayment(request);
        return response;
    }

    @PostMapping("/verify")
    public void verifyPayment(@RequestBody Map<String,String> paymentData){
        orderService.verifyPayment(paymentData,"Paid");
    }

    @GetMapping
    public List<OrderResponse> getUserOrders(){
        return orderService.getUserOrders();
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String orderId){
        orderService.removeOrder(orderId);
    }


    //for admin Panel
    @GetMapping("/all")
    public List<OrderResponse> getAllOrders(){
        return orderService.getOrdersOfAllUsers();
    }


    //for admin Panel
    @PatchMapping("status/{orderId}")
    public void updateOrderStatus(@PathVariable String orderId,@RequestParam String status){
        orderService.updateOrderStatus(orderId,status);
    }











}
