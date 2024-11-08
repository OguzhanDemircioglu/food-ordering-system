package service.srvImpl;

import entity.Order;
import entity.Product;
import entity.Restaurant;
import event.OrderCanceledEvent;
import event.OrderCreatedEvent;
import event.OrderPaidEvent;
import exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;
import service.OrderDomainService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainSrvImpl implements OrderDomainService {

    private static final String UTC = "UTC";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is Initialized", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order, Restaurant restaurant) {
        order.pay();
        log.info("Order with id: {} is Paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is Approved", order.getId().getValue());
    }

    @Override
    public OrderCanceledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order with id: {} is Canceling", order.getId().getValue());
        return new OrderCanceledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is Canceled", order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant is not active " + restaurant.getId().getValue());
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(item -> restaurant.getProducts().forEach(restaurantItem -> {
            Product currentProduct = item.getProduct();
            if (currentProduct.equals(restaurantItem)) {
                currentProduct.updateWithConfirmedNameAndPrice(restaurantItem.getName(),
                        restaurantItem.getPrice());
            }
        }));
    }

}
