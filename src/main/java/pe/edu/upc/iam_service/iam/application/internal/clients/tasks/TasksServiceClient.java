package pe.edu.upc.iam_service.iam.application.internal.clients.tasks;

import pe.edu.upc.iam_service.iam.application.internal.clients.tasks.resources.MemberResource;

import java.util.Optional;

public interface TasksServiceClient {
  Optional<MemberResource> fetchMemberByMemberId(Long memberId, String authorizationHeader);
}
