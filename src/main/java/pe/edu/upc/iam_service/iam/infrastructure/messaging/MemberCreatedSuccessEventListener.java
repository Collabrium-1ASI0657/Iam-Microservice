package pe.edu.upc.iam_service.iam.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pe.edu.upc.iam_service.iam.domain.model.commands.UpdateUserMemberIdCommand;
import pe.edu.upc.iam_service.iam.domain.model.events.MemberCreatedSuccessfullyEvent;
import pe.edu.upc.iam_service.iam.domain.services.UserCommandService;
import pe.edu.upc.iam_service.iam.infrastructure.config.RabbitMQConfig;

@Service
public class MemberCreatedSuccessEventListener {
  private final UserCommandService userCommandService;

  public MemberCreatedSuccessEventListener(UserCommandService userCommandService) {
    this.userCommandService = userCommandService;
  }

  @RabbitListener(queues = RabbitMQConfig.QUEUE_MEMBER_UPDATER)
  public void handleMemberCreatedSuccessfully(MemberCreatedSuccessfullyEvent event) {
    var command = new UpdateUserMemberIdCommand(event.userId(), event.memberId());

    userCommandService.handle(command);
  }
}
