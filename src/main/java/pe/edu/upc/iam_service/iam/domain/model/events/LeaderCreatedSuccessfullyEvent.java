package pe.edu.upc.iam_service.iam.domain.model.events;

public record LeaderCreatedSuccessfullyEvent(Long userId,
                                             Long leaderId) {
}
