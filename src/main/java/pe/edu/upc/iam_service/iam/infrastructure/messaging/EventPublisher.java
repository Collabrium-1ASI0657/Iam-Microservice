package pe.edu.upc.iam_service.iam.infrastructure.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pe.edu.upc.iam_service.iam.infrastructure.config.RabbitMQConfig;

@Service
public class EventPublisher {

  private final RabbitTemplate rabbitTemplate;

  public EventPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void publishEvent(String routingKey, Object event) {
    rabbitTemplate.convertAndSend(
        RabbitMQConfig.EXCHANGE_NAME,
        routingKey,
        event
    );
  }
}