package com.food.ordering.system.order.service.domain.ports.output.messagePublisher.restaurantApproval;

import event.OrderPaidEvent;
import event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
