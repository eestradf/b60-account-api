package com.example.accountapi.infrastructure.client.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TransactionApiRequestDto {
  private String accountId;
  private TransactionType transactionType;
  private BigDecimal amount;
  private LocalDateTime transactionDate;
  private String description;
}
