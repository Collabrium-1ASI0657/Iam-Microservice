package pe.edu.upc.iam_service.iam.interfaces.rest.transform;

import pe.edu.upc.iam_service.iam.domain.model.commands.CreateUserLeaderCommand;

public class CreateUserLeaderCommandFromResourceAssembler {
  public static CreateUserLeaderCommand toCommandFromResource(Long userId) {
    return new CreateUserLeaderCommand(userId);
  }
}
