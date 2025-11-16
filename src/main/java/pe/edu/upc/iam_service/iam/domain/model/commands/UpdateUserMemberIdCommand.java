package pe.edu.upc.iam_service.iam.domain.model.commands;

public record UpdateUserMemberIdCommand(Long userId, Long memberId) {
}
