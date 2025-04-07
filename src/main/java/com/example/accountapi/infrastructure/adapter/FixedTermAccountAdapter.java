package com.example.accountapi.infrastructure.adapter;

import com.example.accountapi.domain.exception.FixedTermAccountNotFoundException;
import com.example.accountapi.domain.model.FixedTermAccount;
import com.example.accountapi.domain.port.out.FixedTermAccountRepositoryPort;
import com.example.accountapi.infrastructure.repository.FixedTermAccountRepository;
import com.example.accountapi.infrastructure.repository.document.FixedTermAccountDocument;
import com.example.accountapi.infrastructure.repository.mapper.FixedTermAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FixedTermAccountAdapter implements FixedTermAccountRepositoryPort {
  private final FixedTermAccountRepository fixedTermAccountRepository;
  private final FixedTermAccountMapper fixedTermAccountMapper;

  @Override
  public Flux<FixedTermAccount> getAllFixedTermAccount() {
    return this.fixedTermAccountRepository
        .findAll()
        .map(this.fixedTermAccountMapper::toFixedTermAccount);
  }

  @Override
  public Mono<FixedTermAccount> getFixedTermAccountById(String id) {
    return this.fixedTermAccountRepository
        .findById(id)
        .map(this.fixedTermAccountMapper::toFixedTermAccount);
  }

  @Override
  public Mono<Void> saveFixedTermAccount(FixedTermAccount fixedTermAccount) {
    FixedTermAccountDocument fixedTermAccountDocument =
        this.fixedTermAccountMapper.toFixedTermAccountDocument(fixedTermAccount);
    fixedTermAccountDocument.setIsActive(Boolean.TRUE);

    return this.fixedTermAccountRepository.save(fixedTermAccountDocument).then();
  }

  @Override
  public Mono<Void> updateFixedTermAccount(String id, FixedTermAccount fixedTermAccount) {
    return this.fixedTermAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new FixedTermAccountNotFoundException()))
        .map(
            existingFixedTermAccountDocument -> {
              existingFixedTermAccountDocument.setAccountNumber(
                  fixedTermAccount.getAccountNumber());
              existingFixedTermAccountDocument.setCustomerId(fixedTermAccount.getCustomerId());
              existingFixedTermAccountDocument.setBalance(fixedTermAccount.getBalance());
              existingFixedTermAccountDocument.setMonthlyMovementLimit(
                  fixedTermAccount.getMonthlyMovementLimit());
              existingFixedTermAccountDocument.setAllowedTransactionDay(
                  fixedTermAccount.getAllowedTransactionDay());

              return existingFixedTermAccountDocument;
            })
        .flatMap(this.fixedTermAccountRepository::save)
        .then();
  }

  @Override
  public Mono<Void> activateFixedTermAccount(String id) {
    return this.fixedTermAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new FixedTermAccountNotFoundException()))
        .map(
            existingFixedTermAccountDocument -> {
              existingFixedTermAccountDocument.setIsActive(Boolean.TRUE);

              return existingFixedTermAccountDocument;
            })
        .flatMap(this.fixedTermAccountRepository::save)
        .then();
  }

  @Override
  public Mono<Void> deactivateFixedTermAccount(String id) {
    return this.fixedTermAccountRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new FixedTermAccountNotFoundException()))
        .map(
            existingFixedTermAccountDocument -> {
              existingFixedTermAccountDocument.setIsActive(Boolean.FALSE);

              return existingFixedTermAccountDocument;
            })
        .flatMap(this.fixedTermAccountRepository::save)
        .then();
  }
}
