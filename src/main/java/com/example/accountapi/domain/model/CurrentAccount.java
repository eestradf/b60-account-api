package com.example.accountapi.domain.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CurrentAccount {
  private String id;
  private String accountNumber;
  private String customerId;
  private BigDecimal balance;
  private AccountType accountType;
  private Integer monthlyMovementLimit;
  private BigDecimal maintenanceCommission;
  private Boolean isActive;
}
