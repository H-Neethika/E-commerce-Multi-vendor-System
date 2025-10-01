package com.neethi.service.impl;

import com.neethi.modal.Cart;
import com.neethi.modal.CartItems;
import com.neethi.modal.Product;
import com.neethi.modal.User;
import com.neethi.repository.CartItemRepository;
import com.neethi.repository.CartRepository;
import com.neethi.service.CartService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItems addCartItem(User user, Product product, String size, int quantity) {

        Cart cart = findUserCart(user);

        CartItems isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

        if (isPresent == null) {

            CartItems cartItem=new CartItems();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setSize(size);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());

            int totalPrice=quantity* product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);


            return  cartItemRepository.save(cartItem);

        }

        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());


        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItems cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }


        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));
        return cart;
    }

    private int calculateDiscountPercentage(int totalPrice, int totalDiscountedPrice) {
    }
}
