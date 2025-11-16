package pe.edu.upc.iam_service.iam.application.internal.clients.groups;

import pe.edu.upc.iam_service.iam.application.internal.clients.groups.resources.LeaderResource;

import java.util.Optional;

public interface GroupsServiceClient {
  Optional<LeaderResource> fetchLeaderByLeaderId(Long leaderId);
}
