package com.example.accountapi.application.controller;

import com.example.accountapi.application.dto.SavingAccountRequestDto;
import com.example.accountapi.application.dto.SavingAccountResponseDto;
import com.example.accountapi.application.mapper.SavingAccountInitMapper;
import com.example.accountapi.domain.model.SavingAccount;
import com.example.accountapi.domain.port.in.SavingAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/savingAccounts")
@RequiredArgsConstructor
public class SavingAccountController {
  private final SavingAccountUseCase savingAccountUseCase;
  private final SavingAccountInitMapper savingAccountInitMapper;

  @GetMapping
  public Flux<SavingAccountResponseDto> getAllSavingAccount() {
    return this.savingAccountUseCase
        .getAllSavingAccount()
        .map(this.savingAccountInitMapper::toSavingAccountResponseDto);
  }

  @GetMapping("/{saving-account-id}")
  public Mono<SavingAccountResponseDto> getSavingAccountById(
      @PathVariable("saving-account-id") String id) {
    return this.savingAccountUseCase
        .getSavingAccountById(id)
        .map(this.savingAccountInitMapper::toSavingAccountResponseDto);
  }

  @PostMapping
  public Mono<Void> createSavingAccountById(
      @RequestBody SavingAccountRequestDto savingAccountRequestDto) {
    SavingAccount savingAccount =
        this.savingAccountInitMapper.toSavingAccount(savingAccountRequestDto);
    return this.savingAccountUseCase.saveSavingAccount(savingAccount);
  }

  @PutMapping("/{saving-account-id}")
  public Mono<Void> updateSavingAccountById(
      @PathVariable("saving-account-id") String id,
      @RequestBody SavingAccountRequestDto savingAccountRequestDto) {
    SavingAccount savingAccount =
        this.savingAccountInitMapper.toSavingAccount(savingAccountRequestDto);
    return this.savingAccountUseCase.updateSavingAccount(id, savingAccount);
  }

  @PutMapping("/{saving-account-id}/activate")
  public Mono<Void> activateSavingAccountById(@PathVariable("saving-account-id") String id) {
    return this.savingAccountUseCase.activateSavingAccount(id);
  }

  @PutMapping("/{saving-account-id}/deactivate")
  public Mono<Void> deactivateSavingAccountById(@PathVariable("saving-account-id") String id) {
    return this.savingAccountUseCase.deactivateSavingAccount(id);
  }
}
