package pe.edu.upc.iam_service.iam.domain.model.queries;

public record GetUserMemberByIdQuery(Long userId,
                                     Long memberId,
                                     String authorizationHeader) {
}
