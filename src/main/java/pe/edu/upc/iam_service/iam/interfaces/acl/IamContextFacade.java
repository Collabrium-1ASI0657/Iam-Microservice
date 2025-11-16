package pe.edu.upc.iam_service.iam.interfaces.acl;

import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;

import java.util.Optional;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other
 *     bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */
public interface IamContextFacade {
  Long fetchUserIdByUsername(String username);
  Optional<User> fetchUserById(Long userId);
}