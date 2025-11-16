package pe.edu.upc.iam_service.iam.application.internal.outboundservices.tokens;

import org.springframework.security.core.Authentication;

/**
 * TokenService interface
 * This interface is used to generate and validate tokens
 */
public interface TokenService {

  /**
   * Generate a token for a given username
   * @param username the username
   * @return String the token
   */
  String generateToken(String username);

  /**
   * This method is responsible for generating a JWT token from an authentication object.
   * @param authentication the authentication object
   * @return String the JWT token
   * @see Authentication
   */
  String generateToken(Authentication authentication);

  /**
   * Extract the username from a token
   * @param token the token
   * @return String the username
   */
  String getUsernameFromToken(String token);

  /**
   * Validate a token
   * @param token the token
   * @return boolean true if the token is valid, false otherwise
   */
  boolean validateToken(String token);
}