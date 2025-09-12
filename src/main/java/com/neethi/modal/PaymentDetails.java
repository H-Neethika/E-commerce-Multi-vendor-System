package com.neethi.modal;

import com.neethi.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {
    private String paymentId;

    private String razorpayPaymentLinkId;

    private String razorpayPaymentLinkReferenceId;

    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;
    private String razorpayPaymentIdZWSP;
    private PaymentStatus status;
}
