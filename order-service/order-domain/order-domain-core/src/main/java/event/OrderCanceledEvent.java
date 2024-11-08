package event;

import entity.Order;

import java.time.ZonedDateTime;

public class OrderCanceledEvent extends OrderEvent {

    public OrderCanceledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
