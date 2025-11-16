package pe.edu.upc.iam_service.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.iam_service.iam.domain.model.queries.*;
import pe.edu.upc.iam_service.iam.domain.services.UserQueryService;
import pe.edu.upc.iam_service.iam.interfaces.rest.resources.UserResource;
import pe.edu.upc.iam_service.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;

/**
 * This class is a REST controller that exposes the users resource.
 * It includes the following operations:
 * - GET /api/v1/users: returns all the users
 * - GET /api/v1/users/{userId}: returns the user with the given id
 **/
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

  private final UserQueryService userQueryService;

  public UsersController(UserQueryService userQueryService) {
    this.userQueryService = userQueryService;
  }

  /**
   * This method returns all the users.
   *
   * @return a list of user resources.
   * @see UserResource
   */
  @GetMapping
  public ResponseEntity<List<UserResource>> getAllUsers(@RequestHeader("Authorization") String authorizationHeader) {
    var getAllUsersQuery = new GetAllUsersQuery();
    var users = userQueryService.handle(getAllUsersQuery);
    var userResources = users.stream()
        .map(user -> {
          if (user.getLeaderId() != null && user.getLeaderId().value() != null) {
            var leaderQuery = new GetUserLeaderByIdQuery(user.getId(), user.getLeaderId().value());
            var userWithLeader = userQueryService.handle(leaderQuery);
            return UserResourceFromEntityAssembler.toResourceFromLeaderDTO(userWithLeader.get());

          } else {
            assert user.getMemberId() != null;
            var memberQuery = new GetUserMemberByIdQuery(user.getId(), user.getMemberId().value(), authorizationHeader);
            var userWithMember = userQueryService.handle(memberQuery);
            return UserResourceFromEntityAssembler.toResourceFromMemberDTO(userWithMember.get());
          }
        })
        .toList();

    return ResponseEntity.ok(userResources);
  }

  /**
   * This method returns the user with the given username.
   *
   * @param username the username.
   * @return the user resource with the given username
   * @see UserResource
   */
  @GetMapping(params = "username")
  public ResponseEntity<UserResource> getUserByUsername(@RequestParam String username,
                                                        @RequestHeader("Authorization") String authorizationHeader) {
    var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
    var user = userQueryService.handle(getUserByUsernameQuery);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    UserResource userResource = null;
    var role = user.get().getRoles().stream().findFirst().get().getName().toString();
    if (role.equals("ROLE_LEADER")) {
      var userWithLeader = userQueryService.handle(new GetUserLeaderByIdQuery(user.get().getId(), user.get().getLeaderId().value()));
      userResource = UserResourceFromEntityAssembler.toResourceFromLeaderDTO(userWithLeader.get());

    }

    if (role.equals("ROLE_MEMBER")) {
      var userWithMember = userQueryService.handle(new GetUserMemberByIdQuery(user.get().getId(),
          user.get().getMemberId().value(), authorizationHeader));
      userResource = UserResourceFromEntityAssembler.toResourceFromMemberDTO(userWithMember.get());
    }

    return ResponseEntity.ok(userResource);
  }

  @GetMapping(params = "memberId")
  public ResponseEntity<UserResource> getUserByMemberId(@RequestParam Long memberId,
                                                        @RequestHeader("Authorization") String authorizationHeader) {
    var getUserByMemberIdQuery = new GetUserByMemberIdQuery(memberId);
    var user = userQueryService.handle(getUserByMemberIdQuery);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    var getUserMemberByIdQuery = new GetUserMemberByIdQuery(user.get().getId(), memberId, authorizationHeader);
    var userWithMember = userQueryService.handle(getUserMemberByIdQuery);

    var userResource = UserResourceFromEntityAssembler.toResourceFromMemberDTO(userWithMember.get());

    return ResponseEntity.ok(userResource);
  }

  /**
   * This method returns the user with the given id.
   *
   * @param userId the user id.
   * @return the user resource with the given id
   * @throws RuntimeException if the user is not found
   * @see UserResource
   */
  @GetMapping(value = "/{userId}")
  public ResponseEntity<UserResource> getUserById(@PathVariable Long userId,
                                                  @RequestHeader("Authorization") String authorizationHeader) {
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var user = userQueryService.handle(getUserByIdQuery);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    UserResource userResource = null;
    var role = user.get().getRoles().stream().findFirst().get().getName().toString();
    if (role.equals("ROLE_LEADER")) {
      var userWithLeader = userQueryService.handle(new GetUserLeaderByIdQuery(userId, user.get().getLeaderId().value()));
      userResource = UserResourceFromEntityAssembler.toResourceFromLeaderDTO(userWithLeader.get());

    }

    if (role.equals("ROLE_MEMBER")) {
      var userWithMember = userQueryService.handle(new GetUserMemberByIdQuery(user.get().getId(),
          user.get().getMemberId().value(), authorizationHeader));
      userResource = UserResourceFromEntityAssembler.toResourceFromMemberDTO(userWithMember.get());
    }

    return ResponseEntity.ok(userResource);
  }
}