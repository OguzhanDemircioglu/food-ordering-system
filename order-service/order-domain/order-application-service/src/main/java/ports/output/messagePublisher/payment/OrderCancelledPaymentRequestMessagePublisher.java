package ports.output.messagePublisher.payment;

import event.OrderCanceledEvent;
import event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCanceledEvent> {
}
