package com.food.ordering.system.order.service.domain.ports.output.repository;

import entity.Order;
import valueObject.TrackingId;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId trackingId);
}