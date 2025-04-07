package com.example.accountapi.application.mapper;

import com.example.accountapi.application.dto.CurrentAccountRequestDto;
import com.example.accountapi.application.dto.CurrentAccountResponseDto;
import com.example.accountapi.application.dto.DepositRequestDto;
import com.example.accountapi.application.dto.WithdrawRequestDto;
import com.example.accountapi.domain.model.CurrentAccount;
import com.example.accountapi.domain.model.Deposit;
import com.example.accountapi.domain.model.Withdraw;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrentAccountInitMapper {
  CurrentAccountResponseDto toCurrentAccountResponseDto(CurrentAccount currentAccount);

  CurrentAccount toCurrentAccount(CurrentAccountRequestDto currentAccountRequestDto);

  Deposit toDeposit(DepositRequestDto depositRequestDto);

  @Mapping(target = "accountId", source = "accountId")
  @Mapping(target = "amount", source = "withdrawRequestDto.amount")
  @Mapping(target = "withdrawDate", source = "withdrawRequestDto.withdrawDate")
  @Mapping(target = "reference", source = "withdrawRequestDto.reference")
  Withdraw toWithdraw(String accountId, WithdrawRequestDto withdrawRequestDto);
}
