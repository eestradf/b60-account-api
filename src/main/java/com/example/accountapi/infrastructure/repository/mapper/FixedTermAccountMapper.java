package com.example.accountapi.infrastructure.repository.mapper;

import com.example.accountapi.domain.model.AccountType;
import com.example.accountapi.domain.model.FixedTermAccount;
import com.example.accountapi.infrastructure.repository.document.FixedTermAccountDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FixedTermAccountMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "accountNumber", source = "accountNumber")
  @Mapping(target = "customerId", source = "customerId")
  @Mapping(target = "balance", source = "balance")
  @Mapping(target = "accountType", source = "accountType")
  @Mapping(target = "monthlyMovementLimit", source = "monthlyMovementLimit")
  @Mapping(target = "allowedTransactionDay", source = "allowedTransactionDay")
  @Mapping(target = "isActive", source = "isActive")
  FixedTermAccount toFixedTermAccount(FixedTermAccountDocument fixedTermAccountDocument);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "accountNumber", source = "accountNumber")
  @Mapping(target = "customerId", source = "customerId")
  @Mapping(target = "balance", source = "balance")
  @Mapping(target = "accountType", source = "accountType")
  @Mapping(target = "monthlyMovementLimit", source = "monthlyMovementLimit")
  @Mapping(target = "allowedTransactionDay", source = "allowedTransactionDay")
  @Mapping(target = "isActive", source = "isActive")
  FixedTermAccountDocument toFixedTermAccountDocument(FixedTermAccount fixedTermAccount);

  default String accountTypeToString(AccountType accountType) {
    if (accountType == null) {
      return null;
    }
    return accountType.name();
  }

  default AccountType stringToAccountType(String accountType) {
    if (accountType == null) {
      return null;
    }
    return AccountType.valueOf(accountType.toUpperCase());
  }
}
