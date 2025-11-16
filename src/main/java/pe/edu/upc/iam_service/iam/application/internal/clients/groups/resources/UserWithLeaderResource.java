package pe.edu.upc.iam_service.iam.application.internal.clients.groups.resources;

import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;

public record UserWithLeaderResource(User user, LeaderResource leader) {
}
