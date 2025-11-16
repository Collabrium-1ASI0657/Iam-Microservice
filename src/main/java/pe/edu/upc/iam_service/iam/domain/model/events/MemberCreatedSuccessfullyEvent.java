package pe.edu.upc.iam_service.iam.domain.model.events;

public record MemberCreatedSuccessfullyEvent(Long userId,
                                             Long memberId) {
}
