package pe.edu.upc.iam_service.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record LeaderId(@NotNull Long value) {
  public LeaderId {
    if (value == null || value <= 0) {
      throw new IllegalArgumentException("Leader ID cannot be null or negative.");
    }
  }
}