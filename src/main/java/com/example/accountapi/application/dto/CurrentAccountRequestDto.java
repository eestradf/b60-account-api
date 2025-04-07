package com.example.accountapi.application.dto;

import com.example.accountapi.domain.model.AccountType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CurrentAccountRequestDto {
  private String accountNumber;
  private String customerId;
  private BigDecimal balance;
  private AccountType accountType;
  private Integer monthlyMovementLimit;
  private BigDecimal maintenanceCommission;
}
