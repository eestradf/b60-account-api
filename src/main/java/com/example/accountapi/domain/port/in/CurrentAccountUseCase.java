package com.example.accountapi.domain.port.in;

import com.example.accountapi.domain.model.CurrentAccount;
import com.example.accountapi.domain.model.Deposit;
import com.example.accountapi.domain.model.Withdraw;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountUseCase {
  Flux<CurrentAccount> getAllCurrentAccount();

  Mono<CurrentAccount> getCurrentAccountById(String id);

  Mono<Void> saveCurrentAccount(CurrentAccount currentAccount);

  Mono<Void> updateCurrentAccount(String id, CurrentAccount currentAccount);

  Mono<Void> activateCurrentAccount(String id);

  Mono<Void> deactivateCurrentAccount(String id);

  Mono<Void> processDeposit(String accountId, Deposit deposit);

  Mono<Void> processWithdraw(Withdraw withdraw);
}
