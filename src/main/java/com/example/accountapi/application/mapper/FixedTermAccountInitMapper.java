package com.example.accountapi.application.mapper;

import com.example.accountapi.application.dto.FixedTermAccountRequestDto;
import com.example.accountapi.application.dto.FixedTermAccountResponseDto;
import com.example.accountapi.domain.model.FixedTermAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FixedTermAccountInitMapper {
  FixedTermAccountResponseDto toFixedTermAccountResponseDto(FixedTermAccount fixedTermAccount);

  FixedTermAccount toFixedTermAccount(FixedTermAccountRequestDto fixedTermAccountRequestDto);
}
