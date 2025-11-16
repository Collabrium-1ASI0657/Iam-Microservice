package pe.edu.upc.iam_service.iam.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.edu.upc.iam_service.iam.domain.model.commands.UpdateUserLeaderIdCommand;
import pe.edu.upc.iam_service.iam.domain.model.events.LeaderCreatedSuccessfullyEvent;
import pe.edu.upc.iam_service.iam.domain.services.UserCommandService;
import pe.edu.upc.iam_service.iam.infrastructure.config.RabbitMQConfig;

@Component
public class LeaderCreatedSuccessEventListener {
  private final UserCommandService userCommandService;

  public LeaderCreatedSuccessEventListener(UserCommandService userCommandService) {
    this.userCommandService = userCommandService;
  }

  @RabbitListener(queues = RabbitMQConfig.QUEUE_LEADER_UPDATER)
  public void handleLeaderCreatedSuccessfully(
      LeaderCreatedSuccessfullyEvent event) {

    System.out.println("✅ Received LeaderCreatedSuccessfullyEvent. Updating User ID: " + event.userId());

    // 💥 PASO CLAVE: Crear un comando para actualizar la tabla User
    var command = new UpdateUserLeaderIdCommand(event.userId(), event.leaderId());

    userCommandService.handle(command); // Necesitas implementar este nuevo método handle
  }
}