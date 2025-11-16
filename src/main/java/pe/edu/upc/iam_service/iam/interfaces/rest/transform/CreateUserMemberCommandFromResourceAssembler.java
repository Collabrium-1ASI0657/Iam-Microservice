package pe.edu.upc.iam_service.iam.interfaces.rest.transform;

import pe.edu.upc.iam_service.iam.domain.model.commands.CreateUserMemberCommand;

public class CreateUserMemberCommandFromResourceAssembler {
  public static CreateUserMemberCommand toCommandFromResource(Long userId) {
    return new CreateUserMemberCommand(userId);
  }
}