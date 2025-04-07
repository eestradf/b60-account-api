package com.example.accountapi.infrastructure.repository.document;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@Document(collection = "fixed_term_account")
public class FixedTermAccountDocument {

  @Id private String id;
  private String accountNumber;
  private String customerId;
  private BigDecimal balance;
  private String accountType;
  private Integer monthlyMovementLimit;
  private Integer allowedTransactionDay;
  private Boolean isActive;
}
