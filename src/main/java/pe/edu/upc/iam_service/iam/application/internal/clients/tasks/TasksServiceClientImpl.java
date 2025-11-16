package pe.edu.upc.iam_service.iam.application.internal.clients.tasks;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pe.edu.upc.iam_service.iam.application.internal.clients.tasks.resources.MemberResource;

import java.util.Optional;

@Service
public class TasksServiceClientImpl implements TasksServiceClient {
  private final WebClient webClient;

  public TasksServiceClientImpl(@Qualifier("loadBalancedWebClientBuilder") WebClient.Builder webClient) {
    this.webClient = webClient
        .baseUrl("http://tasks-service/api/v1")
        .build();;
  }

  @Override
  public Optional<MemberResource> fetchMemberByMemberId(Long memberId, String authorizationHeader) {
    System.out.println("🔍 [1] Starting fetchMemberByMemberId for memberId: " + memberId);
    try {
      var request = webClient.get()
          .uri(uriBuilder -> uriBuilder
              .path("/member/{memberId}")
              .build(memberId));

      MemberResource memberResource = request
          .retrieve()
          .bodyToMono(MemberResource.class)
          .block();

      return Optional.ofNullable(memberResource);

    } catch (WebClientResponseException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        return Optional.empty();
      }
    }
    return Optional.empty();
  }
}
