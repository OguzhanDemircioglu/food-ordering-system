package com.food.ordering.system.order.service.domain.ports.output.messagePublisher.payment;

import event.OrderCanceledEvent;
import event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCanceledEvent> {
}
