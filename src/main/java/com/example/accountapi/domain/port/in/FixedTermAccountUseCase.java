package com.example.accountapi.domain.port.in;

import com.example.accountapi.domain.model.FixedTermAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermAccountUseCase {
  Flux<FixedTermAccount> getAllFixedTermAccount();

  Mono<FixedTermAccount> getFixedTermAccountById(String id);

  Mono<Void> saveFixedTermAccount(FixedTermAccount fixedTermAccount);

  Mono<Void> updateFixedTermAccount(String id, FixedTermAccount fixedTermAccount);

  Mono<Void> activateFixedTermAccount(String id);

  Mono<Void> deactivateFixedTermAccount(String id);
}
