package pe.edu.upc.iam_service.iam.interfaces.rest.resources;

public record UserLeaderResource(
        Long id,
        String averageSolutionTime,
        Integer solvedRequests) {
}