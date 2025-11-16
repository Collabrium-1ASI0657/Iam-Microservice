package pe.edu.upc.iam_service.iam.domain.model.commands;

public record UpdateUserLeaderIdCommand(Long userId, Long leaderId) {
}
