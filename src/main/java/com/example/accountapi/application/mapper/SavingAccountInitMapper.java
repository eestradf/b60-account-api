package com.example.accountapi.application.mapper;

import com.example.accountapi.application.dto.SavingAccountRequestDto;
import com.example.accountapi.application.dto.SavingAccountResponseDto;
import com.example.accountapi.domain.model.SavingAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingAccountInitMapper {
  SavingAccountResponseDto toSavingAccountResponseDto(SavingAccount savingAccount);

  SavingAccount toSavingAccount(SavingAccountRequestDto savingAccountRequestDto);
}
