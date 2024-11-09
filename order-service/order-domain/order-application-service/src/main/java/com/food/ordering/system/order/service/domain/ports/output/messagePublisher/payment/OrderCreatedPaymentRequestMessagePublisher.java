package com.food.ordering.system.order.service.domain.ports.output.messagePublisher.payment;

import event.OrderCreatedEvent;
import event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

}
