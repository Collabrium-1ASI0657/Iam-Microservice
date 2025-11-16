package pe.edu.upc.iam_service.iam.domain.model.queries;

public record GetUserLeaderByIdQuery(Long userId,
                                     Long leaderId) {
}
