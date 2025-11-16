package pe.edu.upc.iam_service.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import pe.edu.upc.iam_service.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.upc.iam_service.iam.infrastructure.tokens.jwt.services.TokenServiceImpl;

/**
 * This interface is a marker interface for the JWT token service.
 * It extends the {@link TokenService} interface.
 * This interface is used to inject the JWT token service in the {@link TokenServiceImpl} class.
 */
public interface BearerTokenService extends TokenService {

  /**
   * This method is responsible for extracting the JWT token from the HTTP request.
   * @param token the HTTP request
   * @return String the JWT token
   */
  String getBearerTokenFrom(HttpServletRequest token);
}