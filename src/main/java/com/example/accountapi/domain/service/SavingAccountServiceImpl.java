package com.example.accountapi.domain.service;

import com.example.accountapi.domain.model.SavingAccount;
import com.example.accountapi.domain.port.in.SavingAccountUseCase;
import com.example.accountapi.domain.port.out.SavingAccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SavingAccountServiceImpl implements SavingAccountUseCase {
  private final SavingAccountRepositoryPort savingAccountRepositoryPort;

  @Override
  public Flux<SavingAccount> getAllSavingAccount() {
    return this.savingAccountRepositoryPort.getAllSavingAccount();
  }

  @Override
  public Mono<SavingAccount> getSavingAccountById(String id) {
    return this.savingAccountRepositoryPort.getSavingAccountById(id);
  }

  @Override
  public Mono<Void> saveSavingAccount(SavingAccount savingAccount) {
    return this.savingAccountRepositoryPort.saveSavingAccount(savingAccount);
  }

  @Override
  public Mono<Void> updateSavingAccount(String id, SavingAccount savingAccount) {
    return this.savingAccountRepositoryPort.updateSavingAccount(id, savingAccount);
  }

  @Override
  public Mono<Void> activateSavingAccount(String id) {
    return this.savingAccountRepositoryPort.activateSavingAccount(id);
  }

  @Override
  public Mono<Void> deactivateSavingAccount(String id) {
    return this.savingAccountRepositoryPort.deactivateSavingAccount(id);
  }
}
