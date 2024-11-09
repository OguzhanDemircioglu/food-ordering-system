package com.food.ordering.system.order.service.domain.srvImpl;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.food.ordering.system.order.service.domain.ports.input.messageListener.payment.PaymentResponseMessageListener;

@Slf4j
@Validated
@Service
public class PaymentResponseMessageListenerSrvImpl implements PaymentResponseMessageListener {
    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {

    }
}
