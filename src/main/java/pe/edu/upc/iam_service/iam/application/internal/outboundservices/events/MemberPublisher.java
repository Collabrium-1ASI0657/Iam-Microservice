package pe.edu.upc.iam_service.iam.application.internal.outboundservices.events;

import org.springframework.stereotype.Service;
import pe.edu.upc.iam_service.iam.domain.model.events.MemberCreatedEvent;
import pe.edu.upc.iam_service.iam.infrastructure.config.RabbitMQConfig;
import pe.edu.upc.iam_service.iam.infrastructure.messaging.EventPublisher;

@Service
public class MemberPublisher {
  private final EventPublisher eventPublisher;

  public MemberPublisher(EventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  public void publishMemberCreated(Long userId) {
    MemberCreatedEvent memberCreatedEvent = new MemberCreatedEvent(userId);
    eventPublisher.publishEvent(RabbitMQConfig.ROUTING_KEY_MEMBER_CREATED, memberCreatedEvent);
  }
}