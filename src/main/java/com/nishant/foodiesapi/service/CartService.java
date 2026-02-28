package com.nishant.foodiesapi.service;

import com.nishant.foodiesapi.IO.CartRequest;
import com.nishant.foodiesapi.IO.CartResponse;

public interface CartService {
    CartResponse addToCart(CartRequest cartRequest);

    CartResponse getCart();

    void clearCart();

    CartResponse removeFromCart(CartRequest cartRequest);

}
