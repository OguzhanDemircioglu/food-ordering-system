package com.food.ordering.system.order.service.domain.handler;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import entity.Order;
import exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import valueObject.TrackingId;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderRepository orderRepository;

    private final OrderDataMapper orderDataMapper;

    @Transactional
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (orderResult.isEmpty()) {
            log.warn("Could not find order with trackingId: {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Could not find order with trackingId: {} " + trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
