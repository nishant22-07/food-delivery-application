package com.nishant.foodiesapi.service;

import com.nishant.foodiesapi.IO.OrderItem;
import com.nishant.foodiesapi.IO.OrderRequest;
import com.nishant.foodiesapi.IO.OrderResponse;
import com.razorpay.RazorpayException;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException;

    void verifyPayment(Map<String,String> paymentData, String status);

    List<OrderResponse> getUserOrders();

    void removeOrder(String orderId);

    List<OrderResponse> getOrdersOfAllUsers();

    void updateOrderStatus(String orderId,String status);


}
