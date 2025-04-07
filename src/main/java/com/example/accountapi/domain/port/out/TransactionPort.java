package com.example.accountapi.domain.port.out;

import com.example.accountapi.domain.model.Deposit;
import reactor.core.publisher.Mono;

public interface TransactionPort {
  Mono<Void> registerDeposit(String accountId, Deposit deposit);
}
