package pe.edu.upc.iam_service.iam.domain.model.queries;

import pe.edu.upc.iam_service.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(
    Roles name) {
}
