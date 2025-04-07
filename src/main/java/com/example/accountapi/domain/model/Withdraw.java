package com.example.accountapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class Withdraw {
    private String accountId;
    private BigDecimal amount;
    private LocalDateTime withdrawDate;
    private String reference;
}