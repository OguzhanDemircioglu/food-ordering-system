package srvImpl;

import dto.create.CreateOrderCommand;
import dto.create.CreateOrderResponse;
import dto.track.TrackOrderQuery;
import dto.track.TrackOrderResponse;
import handler.OrderCreateCommandHandler;
import handler.OrderTrackCommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ports.input.service.OrderApplicationService;

@Slf4j
@Validated
@RequiredArgsConstructor
@Service
class OrderApplicationSrvImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
