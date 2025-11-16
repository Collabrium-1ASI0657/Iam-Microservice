package pe.edu.upc.iam_service.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(
    Long id,
    String username,
    String name,
    String surname,
    String imgUrl,
    String email,
    UserLeaderResource leader,
    UserMemberResource member,
    List<String> roles) {
}