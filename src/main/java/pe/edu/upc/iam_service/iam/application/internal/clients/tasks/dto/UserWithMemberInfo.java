package pe.edu.upc.iam_service.iam.application.internal.clients.tasks.dto;

import pe.edu.upc.iam_service.iam.application.internal.clients.tasks.resources.MemberResource;
import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;

public record UserWithMemberInfo(User user, MemberResource member) {
}
