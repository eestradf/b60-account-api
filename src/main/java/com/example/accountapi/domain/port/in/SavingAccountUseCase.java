package com.example.accountapi.domain.port.in;

import com.example.accountapi.domain.model.SavingAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingAccountUseCase {
  Flux<SavingAccount> getAllSavingAccount();

  Mono<SavingAccount> getSavingAccountById(String id);

  Mono<Void> saveSavingAccount(SavingAccount savingAccount);

  Mono<Void> updateSavingAccount(String id, SavingAccount savingAccount);

  Mono<Void> activateSavingAccount(String id);

  Mono<Void> deactivateSavingAccount(String id);
}
