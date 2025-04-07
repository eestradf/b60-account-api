package com.example.accountapi.domain.service;

import com.example.accountapi.domain.model.FixedTermAccount;
import com.example.accountapi.domain.port.in.FixedTermAccountUseCase;
import com.example.accountapi.domain.port.out.FixedTermAccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FixedTermAccountServiceImpl implements FixedTermAccountUseCase {
  private final FixedTermAccountRepositoryPort fixedTermAccountRepositoryPort;

  @Override
  public Flux<FixedTermAccount> getAllFixedTermAccount() {
    return this.fixedTermAccountRepositoryPort.getAllFixedTermAccount();
  }

  @Override
  public Mono<FixedTermAccount> getFixedTermAccountById(String id) {
    return this.fixedTermAccountRepositoryPort.getFixedTermAccountById(id);
  }

  @Override
  public Mono<Void> saveFixedTermAccount(FixedTermAccount fixedTermAccount) {
    return this.fixedTermAccountRepositoryPort.saveFixedTermAccount(fixedTermAccount);
  }

  @Override
  public Mono<Void> updateFixedTermAccount(String id, FixedTermAccount fixedTermAccount) {
    return this.fixedTermAccountRepositoryPort.updateFixedTermAccount(id, fixedTermAccount);
  }

  @Override
  public Mono<Void> activateFixedTermAccount(String id) {
    return this.fixedTermAccountRepositoryPort.activateFixedTermAccount(id);
  }

  @Override
  public Mono<Void> deactivateFixedTermAccount(String id) {
    return this.fixedTermAccountRepositoryPort.deactivateFixedTermAccount(id);
  }
}
