package pe.edu.upc.iam_service.iam.application.internal.outboundservices.events;

import org.springframework.stereotype.Service;
import pe.edu.upc.iam_service.iam.domain.model.events.LeaderCreatedEvent;
import pe.edu.upc.iam_service.iam.infrastructure.config.RabbitMQConfig;
import pe.edu.upc.iam_service.iam.infrastructure.messaging.EventPublisher;

@Service
public class LeaderPublisher {

  private final EventPublisher eventPublisher;

  public LeaderPublisher(EventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  public void publishLeaderCreated(Long userId) {
    LeaderCreatedEvent event = new LeaderCreatedEvent(userId);
    eventPublisher.publishEvent(RabbitMQConfig.ROUTING_KEY_LEADER_CREATED, event);
  }
}