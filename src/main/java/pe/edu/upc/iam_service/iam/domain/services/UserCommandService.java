package pe.edu.upc.iam_service.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;
import pe.edu.upc.iam_service.iam.domain.model.commands.*;

import java.util.Optional;

public interface UserCommandService {
  /**
   * Handle sign in command
   * This method handles the sign in command and returns the user and the token
   * @return user and token
   */
  Optional<ImmutablePair<User, String>> handle(SignInCommand command);
  /**
   * Handle sign-up command
   * This method handles the sign-up command and returns the user
   * @return user
   */
  Optional<User> handle(SignUpCommand command);

  /**
   * Handle creates user leader command
   * This method handles the create user leader command and returns the user
   * @return user
   */
  Optional<User> handle(CreateUserLeaderCommand command);

  /**
   * Handle creates user member command
   * This method handles the create user member command and returns the user
   * @return user
   */
  Optional<User> handle(CreateUserMemberCommand command);

  Optional<User> handle(UpdateUserLeaderIdCommand command);

  Optional<User> handle(UpdateUserMemberIdCommand command);
}
