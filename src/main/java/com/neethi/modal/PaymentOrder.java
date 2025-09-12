package com.neethi.modal;

import com.neethi.domain.PaymentMethod;
import com.neethi.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    @ManyToOne
    private User user;

    //Set don't allow duplicates (hopping cart items where you might  don't want to add the same product multiple times)
    @OneToMany
    private Set<Order> orders = new HashSet<>();


}
