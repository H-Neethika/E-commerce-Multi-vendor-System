package com.neethi.service;

import com.neethi.modal.Cart;
import com.neethi.modal.CartItems;
import com.neethi.modal.Product;
import com.neethi.modal.User;

public interface CartService {

    public CartItems addCartItem(User user, Product product,String size,int quantity);
    public Cart findUserCart(User user);
}
