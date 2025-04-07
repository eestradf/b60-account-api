package com.example.accountapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Deposit {
  private String id;
  private BigDecimal amount;
  private LocalDateTime depositDate;
  private String reference;
}
