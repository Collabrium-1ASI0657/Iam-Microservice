package pe.edu.upc.iam_service.iam.application.internal.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;
import pe.edu.upc.iam_service.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.iam_service.iam.domain.model.queries.GetUserByUsernameQuery;
import pe.edu.upc.iam_service.iam.domain.services.UserQueryService;
import pe.edu.upc.iam_service.iam.interfaces.acl.IamContextFacade;

import java.util.Optional;

@Service
public class IamContextFacadeImpl implements IamContextFacade {
  private final UserQueryService userQueryService;

  public IamContextFacadeImpl(UserQueryService userQueryService) {
    this.userQueryService = userQueryService;
  }

  /**
   * Fetches the id of the user with the given username.
   * @param username The username of the user.
   * @return The id of the user.
   */
  public Long fetchUserIdByUsername(String username) {
    var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
    var result = userQueryService.handle(getUserByUsernameQuery);
    if (result.isEmpty())
      return 0L;
    return result.get().getId();
  }

  @Override
  public Optional<User> fetchUserById(Long userId) {
    if( userId == null || userId <= 0) {
      return Optional.empty();
    }
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var result = userQueryService.handle(getUserByIdQuery);
    if (result.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(result.get());
  }
}