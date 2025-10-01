package com.neethi.service.impl;

import com.neethi.modal.CartItems;
import com.neethi.modal.User;
import com.neethi.repository.CartItemRepository;
import com.neethi.service.CartItemService;
import com.neethi.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItems updateCartItem(Long userId, Long id, CartItems cartItem) throws Exception {

        CartItems item = findCartItemById(id);

        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getSellingPrice() * item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        throw new Exception("you can't update this cart time");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItems item = findCartItemById(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            cartItemRepository.delete(item);
        }else{
            throw  new Exception("You can't delete this item");
        }
    }

    @Override
    public CartItems findCartItemById(Long id) throws Exception {

        return cartItemRepository.findById(id).orElseThrow(()->new Exception(("Cart item not found with id "+id)));
    }
}
