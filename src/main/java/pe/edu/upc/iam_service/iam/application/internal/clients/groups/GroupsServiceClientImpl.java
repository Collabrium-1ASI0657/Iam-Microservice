package pe.edu.upc.iam_service.iam.application.internal.clients.groups;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pe.edu.upc.iam_service.iam.application.internal.clients.groups.resources.LeaderResource;

import java.util.Optional;

@Service
public class GroupsServiceClientImpl implements GroupsServiceClient {
  private final WebClient webClient;

  public GroupsServiceClientImpl(@Qualifier("loadBalancedWebClientBuilder") WebClient.Builder webClient) {
    this.webClient = webClient
        .baseUrl("http://groups-service/api/v1")
        .build();;
  }

  @Override
  public Optional<LeaderResource> fetchLeaderByLeaderId(Long leaderId) {
    try {
      var request = webClient.get()
          .uri(uriBuilder -> uriBuilder
              .path("/leader/{leaderId}")
              .build(leaderId));

      LeaderResource leaderResource = request
          .retrieve()
          .bodyToMono(LeaderResource.class)
          .block();


      return Optional.ofNullable(leaderResource);
    } catch (WebClientResponseException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        return Optional.empty();
      }
    }
    return Optional.empty();
  }
}
