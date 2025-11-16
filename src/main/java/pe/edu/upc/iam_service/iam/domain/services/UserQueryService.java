package pe.edu.upc.iam_service.iam.domain.services;

import pe.edu.upc.iam_service.iam.application.internal.clients.groups.resources.UserWithLeaderResource;
import pe.edu.upc.iam_service.iam.application.internal.clients.tasks.dto.UserWithMemberInfo;
import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;
import pe.edu.upc.iam_service.iam.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
  List<User> handle(GetAllUsersQuery query);
  Optional<User> handle(GetUserByIdQuery query);
  Optional<User> handle(GetUserByUsernameQuery query);
  Optional<User> handle(GetUserByMemberIdQuery query);
  Optional<User> handle(GetUserByLeaderIdQuery query);
  Optional<UserWithLeaderResource> handle(GetUserLeaderByIdQuery query);
  Optional<UserWithMemberInfo> handle(GetUserMemberByIdQuery query);
}