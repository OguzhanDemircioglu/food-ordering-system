package ports.output.messagePublisher.payment;

import event.OrderCreatedEvent;
import event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

}
