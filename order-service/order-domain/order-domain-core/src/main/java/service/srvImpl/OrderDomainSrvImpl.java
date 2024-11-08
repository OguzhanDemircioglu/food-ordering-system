package service.srvImpl;

import entity.Order;
import entity.Restaurant;
import event.OrderCanceledEvent;
import event.OrderCreatedEvent;
import event.OrderPaidEvent;
import service.OrderDomainService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainSrvImpl implements OrderDomainService {
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order,restaurant);
        order.validateOrder();
        order.initializeOrder();
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public OrderPaidEvent payOrder(Order order, Restaurant restaurant) {
        return null;
    }

    @Override
    public void approveOrder(Order order) {

    }

    @Override
    public OrderCanceledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        return null;
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {

    }

    private void validateRestaurant(Restaurant restaurant) {
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
    }

}
