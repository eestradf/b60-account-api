package com.example.accountapi.infrastructure.client.transaction.mapper;

import com.example.accountapi.domain.model.Deposit;
import com.example.accountapi.infrastructure.client.transaction.dto.TransactionApiRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
  TransactionApiRequestDto toTransactionApiRequestDto(String accountId, Deposit deposit);
}
