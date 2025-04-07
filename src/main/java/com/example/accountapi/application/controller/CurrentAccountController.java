package com.example.accountapi.application.controller;

import com.example.accountapi.application.dto.CurrentAccountRequestDto;
import com.example.accountapi.application.dto.CurrentAccountResponseDto;
import com.example.accountapi.application.dto.DepositRequestDto;
import com.example.accountapi.application.dto.WithdrawRequestDto;
import com.example.accountapi.application.mapper.CurrentAccountInitMapper;
import com.example.accountapi.domain.model.CurrentAccount;
import com.example.accountapi.domain.model.Deposit;
import com.example.accountapi.domain.model.Withdraw;
import com.example.accountapi.domain.port.in.CurrentAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/current-accounts")
@RequiredArgsConstructor
public class CurrentAccountController {
  private final CurrentAccountUseCase currentAccountUseCase;
  private final CurrentAccountInitMapper currentAccountInitMapper;

  @GetMapping
  public Flux<CurrentAccountResponseDto> getAllCurrentAccount() {
    return this.currentAccountUseCase
        .getAllCurrentAccount()
        .map(this.currentAccountInitMapper::toCurrentAccountResponseDto);
  }

  @GetMapping("/{current-account-id}")
  public Mono<CurrentAccountResponseDto> getCurrentAccountById(
      @PathVariable("current-account-id") String id) {
    return this.currentAccountUseCase
        .getCurrentAccountById(id)
        .map(this.currentAccountInitMapper::toCurrentAccountResponseDto);
  }

  @PostMapping
  public Mono<Void> createCurrentAccountById(
      @RequestBody CurrentAccountRequestDto currentAccountRequestDto) {
    CurrentAccount currentAccount =
        this.currentAccountInitMapper.toCurrentAccount(currentAccountRequestDto);
    return this.currentAccountUseCase.saveCurrentAccount(currentAccount);
  }

  @PutMapping("/{current-account-id}")
  public Mono<Void> updateCurrentAccountById(
      @PathVariable("current-account-id") String id,
      @RequestBody CurrentAccountRequestDto currentAccountRequestDto) {
    CurrentAccount currentAccount =
        this.currentAccountInitMapper.toCurrentAccount(currentAccountRequestDto);
    return this.currentAccountUseCase.updateCurrentAccount(id, currentAccount);
  }

  @PutMapping("/{current-account-id}/activate")
  public Mono<Void> activateCurrentAccountById(@PathVariable("current-account-id") String id) {
    return this.currentAccountUseCase.activateCurrentAccount(id);
  }

  @PutMapping("/{current-account-id}/deactivate")
  public Mono<Void> deactivateCurrentAccountById(@PathVariable("current-account-id") String id) {
    return this.currentAccountUseCase.deactivateCurrentAccount(id);
  }

  @PostMapping("/{current-account-id}/deposit")
  public Mono<Void> currentAccountMakeDeposit(
      @PathVariable("current-account-id") String accountId,
      @RequestBody DepositRequestDto depositRequestDto) {
    Deposit deposit = this.currentAccountInitMapper.toDeposit(depositRequestDto);

    return this.currentAccountUseCase.processDeposit(accountId, deposit);
  }

  @PostMapping("/{current-account-id}/withdraw")
  public Mono<Void> currentAccountWithdraw(
          @PathVariable("current-account-id") String accountId,
          @RequestBody WithdrawRequestDto withdrawRequestDto) {
    Withdraw withdraw = this.currentAccountInitMapper.toWithdraw(accountId, withdrawRequestDto);

    return this.currentAccountUseCase.processWithdraw(withdraw);
  }
}
