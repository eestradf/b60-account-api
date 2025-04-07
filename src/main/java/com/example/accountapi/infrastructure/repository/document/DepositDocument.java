package com.example.accountapi.infrastructure.repository.document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@Document(collection = "deposit")
public class DepositDocument {

  @Id private String id;
  private String accountId;
  private BigDecimal amount;
  private LocalDateTime depositDate;
  private String reference;
}
