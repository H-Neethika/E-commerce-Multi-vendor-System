package com.neethi.service;

import com.neethi.modal.CartItems;

public interface CartItemService {

    CartItems updateCartItem(Long userId, Long id, CartItems cartItem) throws Exception;

    void removeCartItem(Long userId, Long cartItemId) throws Exception;

    CartItems findCartItemById(Long id) throws Exception;
}
