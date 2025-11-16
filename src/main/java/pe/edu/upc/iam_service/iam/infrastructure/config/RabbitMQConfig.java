package pe.edu.upc.iam_service.iam.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  public static final String EXCHANGE_NAME = "iam-events-exchange";

  // Eventos que IAM publica
  public static final String ROUTING_KEY_LEADER_CREATED = "leader.created";
  public static final String ROUTING_KEY_MEMBER_CREATED = "member.created";

  // Eventos que IAM escucha (respuestas)
  public static final String ROUTING_KEY_LEADER_CREATED_SUCCESS = "leader.created.success";
  public static final String ROUTING_KEY_MEMBER_CREATED_SUCCESS = "member.created.success";

  // Colas para escuchar respuestas
  public static final String QUEUE_LEADER_UPDATER = "iam.leader-updater";
  public static final String QUEUE_MEMBER_UPDATER = "iam.member-updater";

  // -------------------------------------------------------
  // BEANS DE INFRAESTRUCTURA
  // -------------------------------------------------------
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(EXCHANGE_NAME);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public Queue leaderUpdaterQueue() {
    return new Queue(QUEUE_LEADER_UPDATER, true);
  }

  @Bean
  public Queue memberUpdaterQueue() {
    return new Queue(QUEUE_MEMBER_UPDATER, true);
  }

  @Bean
  public Binding leaderUpdaterBinding(Queue leaderUpdaterQueue, TopicExchange exchange) {
    return BindingBuilder.bind(leaderUpdaterQueue)
        .to(exchange)
        .with(ROUTING_KEY_LEADER_CREATED_SUCCESS);
  }

  @Bean
  public Binding memberUpdaterBinding(Queue memberUpdaterQueue, TopicExchange exchange) {
    return BindingBuilder.bind(memberUpdaterQueue)
        .to(exchange)
        .with(ROUTING_KEY_MEMBER_CREATED_SUCCESS);
  }
}
