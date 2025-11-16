package pe.edu.upc.iam_service.iam.domain.model.commands;

import pe.edu.upc.iam_service.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(
    String username,
    String name,
    String surname,
    String imgUrl,
    String email,
    String password,
    List<Role> roles) {
}
