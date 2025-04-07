package com.example.accountapi.infrastructure.repository.document;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Document(collection = "withdraw")
public class WithdrawDocument {
    @Id
    private String id;
    private String accountId;
    private BigDecimal amount;
    private LocalDateTime withdrawDate;
    private String reference;
}
