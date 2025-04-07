package com.example.accountapi.infrastructure.adapter;

import com.example.accountapi.domain.exception.SavingAccountNotFoundException;
import com.example.accountapi.domain.model.SavingAccount;
import com.example.accountapi.domain.port.out.SavingAccountRepositoryPort;
import com.example.accountapi.infrastructure.repository.SavingAccountRepository;
import com.example.accountapi.infrastructure.repository.document.SavingAccountDocument;
import com.example.accountapi.infrastructure.repository.mapper.SavingAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SavingAccountAdapter implements SavingAccountRepositoryPort {
  private final SavingAccountRepository savingAccountRepository;
  private final SavingAccountMapper savingAccountMapper;

  @Override
  public Flux<SavingAccount> getAllSavingAccount() {
    return this.savingAccountRepository.findAll().map(this.savingAccountMapper::toSavingAccount);
  }

  @Override
  public Mono<SavingAccount> getSavingAccountById(String id) {
    return this.savingAccountRepository.findById(id).map(this.savingAccountMapper::toSavingAccount);
  }

  @Override
  public Mono<Void> saveSavingAccount(SavingAccount savingAccount) {
    SavingAccountDocument savingAccountDocument =
        this.savingAccountMapper.toSavingAccountDocument(savingAccount);
    savingAccountDocument.setIsActive(Boolean.TRUE);

    return this.savingAccountRepository.save(savingAccountDocument).then();
  }

  @Override
  public Mono<Void> updateSavingAccount(String id, SavingAccount savingAccount) {
    return this.savingAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new SavingAccountNotFoundException()))
        .map(
            existingSavingAccountDocument -> {
              existingSavingAccountDocument.setAccountNumber(savingAccount.getAccountNumber());
              existingSavingAccountDocument.setCustomerId(savingAccount.getCustomerId());
              existingSavingAccountDocument.setBalance(savingAccount.getBalance());
              existingSavingAccountDocument.setMonthlyMovementLimit(
                  savingAccount.getMonthlyMovementLimit());

              return existingSavingAccountDocument;
            })
        .flatMap(this.savingAccountRepository::save)
        .then();
  }

  @Override
  public Mono<Void> activateSavingAccount(String id) {
    return this.savingAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new SavingAccountNotFoundException()))
        .map(
            existingSavingAccountDocument -> {
              existingSavingAccountDocument.setIsActive(Boolean.TRUE);

              return existingSavingAccountDocument;
            })
        .flatMap(this.savingAccountRepository::save)
        .then();
  }

  @Override
  public Mono<Void> deactivateSavingAccount(String id) {
    return this.savingAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new SavingAccountNotFoundException()))
        .map(
            existingSavingAccountDocument -> {
              existingSavingAccountDocument.setIsActive(Boolean.FALSE);

              return existingSavingAccountDocument;
            })
        .flatMap(this.savingAccountRepository::save)
        .then();
  }
}
