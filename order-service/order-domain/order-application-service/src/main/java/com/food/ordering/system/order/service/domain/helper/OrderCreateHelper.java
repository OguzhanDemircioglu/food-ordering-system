package com.food.ordering.system.order.service.domain.helper;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import entity.Customer;
import entity.Order;
import entity.Restaurant;
import event.OrderCreatedEvent;
import exception.OrderDomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.message.ApplicationDomainEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;
import service.OrderDomainService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomerExist(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order created event: {}", orderCreatedEvent.getOrder().getId().getValue());
        applicationDomainEventPublisher.publish(orderCreatedEvent);
        return orderCreatedEvent;
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        if (optionalRestaurant.isEmpty()) {
            log.warn("Could not find restaurant information for restaurant {}", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Could not find restaurant information for restaurant {} " + createOrderCommand.getRestaurantId());
        }
        return optionalRestaurant.get();
    }

    private void checkCustomerExist(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Customer not found", customerId);
            throw new OrderDomainException("Customer not found with customerId: " + customerId);
        }
    }

    private void saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        if (savedOrder == null) {
            log.warn("Could not save order{} orderId: ", order.getId().getValue());
            throw new OrderDomainException("Could not save order");
        }
        log.info("Saved order {}", savedOrder.getId().getValue());
    }
}
