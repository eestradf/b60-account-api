package com.example.accountapi.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DepositRequestDto {
  private BigDecimal amount;
  private LocalDateTime depositDate;
  private String reference;
}
