package com.example.accountapi.infrastructure.adapter;

import com.example.accountapi.domain.model.Deposit;
import com.example.accountapi.domain.port.out.TransactionPort;
import com.example.accountapi.infrastructure.client.transaction.dto.TransactionApiRequestDto;
import com.example.accountapi.infrastructure.client.transaction.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransactionAdapter implements TransactionPort {
  @Value("transaction-api.resource.register-deposit")
  private String resource;

  private final WebClient webClientTransaction;
  private final TransactionMapper transactionMapper;

  @Override
  public Mono<Void> registerDeposit(String accountId, Deposit deposit) {
    TransactionApiRequestDto transactionApiRequestDto =
        this.transactionMapper.toTransactionApiRequestDto(accountId, deposit);

    return this.webClientTransaction
        .post()
        .uri(this.resource)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(transactionApiRequestDto), TransactionApiRequestDto.class)
        .retrieve()
        .bodyToMono(Void.class);
  }
}
