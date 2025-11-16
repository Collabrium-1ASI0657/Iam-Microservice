package pe.edu.upc.iam_service.iam.interfaces.rest.transform;

import pe.edu.upc.iam_service.iam.application.internal.clients.groups.resources.UserWithLeaderResource;
import pe.edu.upc.iam_service.iam.application.internal.clients.tasks.dto.UserWithMemberInfo;
import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;
import pe.edu.upc.iam_service.iam.domain.model.entities.Role;
import pe.edu.upc.iam_service.iam.interfaces.rest.resources.UserLeaderResource;
import pe.edu.upc.iam_service.iam.interfaces.rest.resources.UserMemberResource;
import pe.edu.upc.iam_service.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();

    // Define constantes para evitar "magic strings"
    final String LEADER_ROLE = "ROLE_LEADER";
    final String MEMBER_ROLE = "ROLE_MEMBER";

    // Rellenar UserLeaderResource solo si el usuario tiene el rol "leader"
    UserLeaderResource leaderResource = roles.contains(LEADER_ROLE) ?
        new UserLeaderResource(
            10L,
            "19:00:00", // Asumiendo que este valor es fijo o se obtiene de otro lado
            0) : null;

    // Rellenar UserMemberResource solo si el usuario tiene el rol "member"
    UserMemberResource memberResource = roles.contains(MEMBER_ROLE) ?
        new UserMemberResource(11L,
            0L) : null;

    return new UserResource(
        user.getId(),
        user.getUsername(),
        user.getName(),
        user.getSurname(),
        user.getImgUrl(),
        user.getEmail(),
        leaderResource,
        memberResource,
        roles
    );
  }

  public static UserResource toResourceFromLeaderDTO(UserWithLeaderResource dto){
    var user = dto.user();
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();
    var leader = dto.leader();

    UserLeaderResource leaderResource = new UserLeaderResource(
        leader.id(),
        leader.averageSolutionTime(),
        leader.solvedRequests()
    );

    return new UserResource(
        user.getId(),
        user.getUsername(),
        user.getName(),
        user.getSurname(),
        user.getImgUrl(),
        user.getEmail(),
        leaderResource,
        null,
        roles
    );
  }

  public static UserResource toResourceFromMemberDTO(UserWithMemberInfo dto){
    var user = dto.user();
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();
    var member = dto.member();

    UserMemberResource userMemberResource = new UserMemberResource(
        member.id(),
        member.groupId()
    );

    return new UserResource(
        user.getId(),
        user.getUsername(),
        user.getName(),
        user.getSurname(),
        user.getImgUrl(),
        user.getEmail(),
        null,
        userMemberResource,
        roles
    );
  }
}