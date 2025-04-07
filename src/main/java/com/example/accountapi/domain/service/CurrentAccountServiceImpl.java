package com.example.accountapi.domain.service;

import com.example.accountapi.domain.model.CurrentAccount;
import com.example.accountapi.domain.model.Deposit;
import com.example.accountapi.domain.model.Withdraw;
import com.example.accountapi.domain.port.in.CurrentAccountUseCase;
import com.example.accountapi.domain.port.out.CurrentAccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrentAccountServiceImpl implements CurrentAccountUseCase {
  private final CurrentAccountRepositoryPort currentAccountRepositoryPort;

  @Override
  public Flux<CurrentAccount> getAllCurrentAccount() {
    return this.currentAccountRepositoryPort.getAllCurrentAccount();
  }

  @Override
  public Mono<CurrentAccount> getCurrentAccountById(String id) {
    return this.currentAccountRepositoryPort.getCurrentAccountById(id);
  }

  @Override
  public Mono<Void> saveCurrentAccount(CurrentAccount currentAccount) {
    return this.currentAccountRepositoryPort.saveCurrentAccount(currentAccount);
  }

  @Override
  public Mono<Void> updateCurrentAccount(String id, CurrentAccount currentAccount) {
    return this.currentAccountRepositoryPort.updateCurrentAccount(id, currentAccount);
  }

  @Override
  public Mono<Void> activateCurrentAccount(String id) {
    return this.currentAccountRepositoryPort.activateCurrentAccount(id);
  }

  @Override
  public Mono<Void> deactivateCurrentAccount(String id) {
    return this.currentAccountRepositoryPort.deactivateCurrentAccount(id);
  }

  @Override
  public Mono<Void> processDeposit(String accountId, Deposit deposit) {
    return this.currentAccountRepositoryPort.registerDeposit(accountId, deposit);
  }

  @Override
  public Mono<Void> processWithdraw(Withdraw withdraw) {
    return null;
  }
}
