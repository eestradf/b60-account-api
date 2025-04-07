package com.example.accountapi.application.controller;

import com.example.accountapi.application.dto.FixedTermAccountRequestDto;
import com.example.accountapi.application.dto.FixedTermAccountResponseDto;
import com.example.accountapi.application.mapper.FixedTermAccountInitMapper;
import com.example.accountapi.domain.model.FixedTermAccount;
import com.example.accountapi.domain.port.in.FixedTermAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fixedTermAccounts")
@RequiredArgsConstructor
public class FixedTermAccountController {
  private final FixedTermAccountUseCase fixedTermAccountUseCase;
  private final FixedTermAccountInitMapper fixedTermAccountInitMapper;

  @GetMapping
  public Flux<FixedTermAccountResponseDto> getAllFixedTermAccount() {
    return this.fixedTermAccountUseCase
        .getAllFixedTermAccount()
        .map(this.fixedTermAccountInitMapper::toFixedTermAccountResponseDto);
  }

  @GetMapping("/{fixed-term-account-id}")
  public Mono<FixedTermAccountResponseDto> getFixedTermAccountById(
      @PathVariable("fixed-term-account-id") String id) {
    return this.fixedTermAccountUseCase
        .getFixedTermAccountById(id)
        .map(this.fixedTermAccountInitMapper::toFixedTermAccountResponseDto);
  }

  @PostMapping
  public Mono<Void> createFixedTermAccountById(
      @RequestBody FixedTermAccountRequestDto fixedTermAccountRequestDto) {
    FixedTermAccount fixedTermAccount =
        this.fixedTermAccountInitMapper.toFixedTermAccount(fixedTermAccountRequestDto);
    return this.fixedTermAccountUseCase.saveFixedTermAccount(fixedTermAccount);
  }

  @PutMapping("/{fixed-term-account-id}")
  public Mono<Void> updateFixedTermAccountById(
      @PathVariable("fixed-term-account-id") String id,
      @RequestBody FixedTermAccountRequestDto fixedTermAccountRequestDto) {
    FixedTermAccount fixedTermAccount =
        this.fixedTermAccountInitMapper.toFixedTermAccount(fixedTermAccountRequestDto);
    return this.fixedTermAccountUseCase.updateFixedTermAccount(id, fixedTermAccount);
  }

  @PutMapping("/{fixed-term-account-id}/activate")
  public Mono<Void> activateFixedTermAccountById(@PathVariable("fixed-term-account-id") String id) {
    return this.fixedTermAccountUseCase.activateFixedTermAccount(id);
  }

  @PutMapping("/{fixed-term-account-id}/deactivate")
  public Mono<Void> deactivateFixedTermAccountById(
      @PathVariable("fixed-term-account-id") String id) {
    return this.fixedTermAccountUseCase.deactivateFixedTermAccount(id);
  }
}
