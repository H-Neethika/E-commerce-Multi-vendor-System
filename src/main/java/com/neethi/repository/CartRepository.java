package com.neethi.repository;

import com.neethi.modal.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUserId(Long id);
}
