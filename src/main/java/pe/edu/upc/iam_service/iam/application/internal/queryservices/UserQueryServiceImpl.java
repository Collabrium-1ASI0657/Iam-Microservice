package pe.edu.upc.iam_service.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.iam_service.iam.application.internal.clients.groups.GroupsServiceClient;
import pe.edu.upc.iam_service.iam.application.internal.clients.groups.resources.UserWithLeaderResource;
import pe.edu.upc.iam_service.iam.application.internal.clients.tasks.TasksServiceClient;
import pe.edu.upc.iam_service.iam.application.internal.clients.tasks.dto.UserWithMemberInfo;
import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;
import pe.edu.upc.iam_service.iam.domain.model.queries.*;
import pe.edu.upc.iam_service.iam.domain.model.valueobjects.LeaderId;
import pe.edu.upc.iam_service.iam.domain.model.valueobjects.MemberId;
import pe.edu.upc.iam_service.iam.domain.services.UserQueryService;
import pe.edu.upc.iam_service.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserQueryService} interface.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
  private final UserRepository userRepository;
  private final GroupsServiceClient groupsServiceClient;
  private final TasksServiceClient tasksServiceClient;

  /**
   * Constructor.
   *
   * @param userRepository {@link UserRepository} instance.
   */
  public UserQueryServiceImpl(UserRepository userRepository,
                              GroupsServiceClient groupsServiceClient,
                              TasksServiceClient tasksServiceClient) {
    this.userRepository = userRepository;
    this.groupsServiceClient = groupsServiceClient;
    this.tasksServiceClient = tasksServiceClient;
  }

  /**
   * This method is used to handle {@link GetAllUsersQuery} query.
   * @param query {@link GetAllUsersQuery} instance.
   * @return {@link List} of {@link User} instances.
   * @see GetAllUsersQuery
   */
  @Override
  public List<User> handle(GetAllUsersQuery query) {
    return userRepository.findAll();
  }

  /**
   * This method is used to handle {@link GetUserByIdQuery} query.
   * @param query {@link GetUserByIdQuery} instance.
   * @return {@link Optional} of {@link User} instance.
   * @see GetUserByIdQuery
   */
  @Override
  public Optional<User> handle(GetUserByIdQuery query) {
    return userRepository.findById(query.userId());
  }

  /**
   * This method is used to handle {@link GetUserByUsernameQuery} query.
   * @param query {@link GetUserByUsernameQuery} instance.
   * @return {@link Optional} of {@link User} instance.
   * @see GetUserByUsernameQuery
   */
  @Override
  public Optional<User> handle(GetUserByUsernameQuery query) {
    return userRepository.findByUsername(query.username());
  }

  /**
   * This method is used to handle {@link GetUserByMemberIdQuery} query.
   * @param query {@link GetUserByMemberIdQuery} instance.
   * @return {@link Optional} of {@link User} instance.
   * @see GetUserByMemberIdQuery
   */
  @Override
  public Optional<User> handle(GetUserByMemberIdQuery query) {
    return userRepository.findByMemberId(new MemberId(query.memberId()));
  }

  /**
   * This method is used to handle {@link GetUserByLeaderIdQuery} query.
   * @param query {@link GetUserByLeaderIdQuery} instance.
   * @return {@link Optional} of {@link User} instance.
   * @see GetUserByLeaderIdQuery
   */
  @Override
  public Optional<User> handle(GetUserByLeaderIdQuery query) {
    return userRepository.findByLeaderId(new LeaderId(query.leaderId()));
  }

  @Override
  public Optional<UserWithLeaderResource> handle(GetUserLeaderByIdQuery query) {
    var user = userRepository.findById(query.userId());
    var leader = groupsServiceClient.fetchLeaderByLeaderId(query.leaderId());

    Optional<UserWithLeaderResource> userLeader = Optional.empty();
    if (user.isPresent() && leader.isPresent()) {
      var resource = new UserWithLeaderResource(user.get(), leader.get());
      userLeader = Optional.of(resource);
    }

    return userLeader;
  }

  @Override
  public Optional<UserWithMemberInfo> handle(GetUserMemberByIdQuery query) {
    var user = userRepository.findById(query.userId());
    var member = tasksServiceClient.fetchMemberByMemberId(query.memberId(), query.authorizationHeader());

    Optional<UserWithMemberInfo> userMember = Optional.empty();
    if (user.isPresent() && member.isPresent()) {
      var resource = new UserWithMemberInfo(user.get(), member.get());
      userMember = Optional.of(resource);
    }

    return userMember;
  }
}