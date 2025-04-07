package com.example.accountapi.infrastructure.adapter;

import com.example.accountapi.domain.exception.CurrentAccountNotFoundException;
import com.example.accountapi.domain.model.CurrentAccount;
import com.example.accountapi.domain.model.Deposit;
import com.example.accountapi.domain.model.Withdraw;
import com.example.accountapi.domain.port.out.CurrentAccountRepositoryPort;
import com.example.accountapi.infrastructure.repository.CurrentAccountRepository;
import com.example.accountapi.infrastructure.repository.DepositRepository;
import com.example.accountapi.infrastructure.repository.WithdrawRepository;
import com.example.accountapi.infrastructure.repository.document.CurrentAccountDocument;
import com.example.accountapi.infrastructure.repository.document.DepositDocument;
import com.example.accountapi.infrastructure.repository.document.WithdrawDocument;
import com.example.accountapi.infrastructure.repository.mapper.CurrentAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CurrentAccountAdapter implements CurrentAccountRepositoryPort {
  private final CurrentAccountRepository currentAccountRepository;
  private final DepositRepository depositRepository;
  private final WithdrawRepository withdrawRepository;
  private final CurrentAccountMapper currentAccountMapper;

  @Override
  public Flux<CurrentAccount> getAllCurrentAccount() {
    return this.currentAccountRepository.findAll().map(this.currentAccountMapper::toCurrentAccount);
  }

  @Override
  public Mono<CurrentAccount> getCurrentAccountById(String id) {
    return this.currentAccountRepository
        .findById(id)
        .map(this.currentAccountMapper::toCurrentAccount);
  }

  @Override
  public Mono<Void> saveCurrentAccount(CurrentAccount currentAccount) {
    CurrentAccountDocument currentAccountDocument =
        this.currentAccountMapper.toCurrentAccountDocument(currentAccount);
    currentAccountDocument.setIsActive(Boolean.TRUE);

    return this.currentAccountRepository.save(currentAccountDocument).then();
  }

  @Override
  public Mono<Void> updateCurrentAccount(String id, CurrentAccount currentAccount) {
    return this.currentAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new CurrentAccountNotFoundException()))
        .map(
            existingCurrentAccountDocument -> {
              existingCurrentAccountDocument.setAccountNumber(currentAccount.getAccountNumber());
              existingCurrentAccountDocument.setCustomerId(currentAccount.getCustomerId());
              existingCurrentAccountDocument.setBalance(currentAccount.getBalance());
              existingCurrentAccountDocument.setMonthlyMovementLimit(
                  currentAccount.getMonthlyMovementLimit());
              existingCurrentAccountDocument.setMaintenanceCommission(
                  currentAccount.getMaintenanceCommission());

              return existingCurrentAccountDocument;
            })
        .flatMap(this.currentAccountRepository::save)
        .then();
  }

  @Override
  public Mono<Void> activateCurrentAccount(String id) {
    return this.currentAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new CurrentAccountNotFoundException()))
        .map(
            existingCurrentAccountDocument -> {
              existingCurrentAccountDocument.setIsActive(Boolean.TRUE);

              return existingCurrentAccountDocument;
            })
        .flatMap(this.currentAccountRepository::save)
        .then();
  }

  @Override
  public Mono<Void> deactivateCurrentAccount(String id) {
    return this.currentAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new CurrentAccountNotFoundException()))
        .map(
            existingCurrentAccountDocument -> {
              existingCurrentAccountDocument.setIsActive(Boolean.FALSE);

              return existingCurrentAccountDocument;
            })
        .flatMap(this.currentAccountRepository::save)
        .then();
  }

  @Override
  public Mono<Void> registerDeposit(String accountId, Deposit deposit) {
    DepositDocument depositDocument = currentAccountMapper.toDepositDocument(accountId, deposit);

    return this.depositRepository.save(depositDocument).then();
  }

    @Override
    public Mono<Void> registerWithdraw(Withdraw withdraw) {
        WithdrawDocument withdrawDocument = this.currentAccountMapper.toWithdrawDocument(withdraw);

        return this.withdrawRepository.save(withdrawDocument)
                .then();
    }
}
