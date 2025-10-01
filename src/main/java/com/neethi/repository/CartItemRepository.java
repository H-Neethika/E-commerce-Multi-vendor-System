package com.neethi.repository;

import com.neethi.modal.Cart;
import com.neethi.modal.CartItems;
import com.neethi.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems,Long> {


    CartItems findByCartAndProductAndSize(Cart cart, Product product,String size);
}
