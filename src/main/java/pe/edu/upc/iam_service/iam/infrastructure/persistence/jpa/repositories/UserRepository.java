package pe.edu.upc.iam_service.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.iam_service.iam.domain.model.aggregates.User;
import pe.edu.upc.iam_service.iam.domain.model.valueobjects.LeaderId;
import pe.edu.upc.iam_service.iam.domain.model.valueobjects.MemberId;

import java.util.Optional;

/**
 * This interface is responsible for providing the User entity-related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
  /**
   * This method is responsible for finding the user by username.
   * @param username The username.
   * @return The user object.
   */
  Optional<User> findByUsername(String username);

  /**
   * This method is responsible for checking if the user exists by username.
   * @param username The username.
   * @return True if the user exists, false otherwise.
   */
  boolean existsByUsername(String username);

  /**
   * This method is responsible for checking if the user exists by email.
   * @param email The email.
   * @return True if the user exists, false otherwise.
   */
  boolean existsByEmail(String email);

  /**
   * This method is responsible for finding the user by member ID.
   * @param memberId The member ID.
   * @return The user object.
   */
  Optional<User> findByMemberId(MemberId memberId);

  /**
   * This method is responsible for finding the user by leader ID.
   * @param leaderId The leader ID.
   * @return The user object.
   */
  Optional<User> findByLeaderId(LeaderId leaderId);
}