package com.example.accountapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class WithdrawRequestDto {
    private BigDecimal amount;
    private LocalDateTime withdrawDate;
    private String reference;
}
