package com.example.accountapi.infrastructure.repository.mapper;

import com.example.accountapi.domain.model.AccountType;
import com.example.accountapi.domain.model.CurrentAccount;
import com.example.accountapi.domain.model.Deposit;
import com.example.accountapi.domain.model.Withdraw;
import com.example.accountapi.infrastructure.repository.document.CurrentAccountDocument;
import com.example.accountapi.infrastructure.repository.document.DepositDocument;
import com.example.accountapi.infrastructure.repository.document.WithdrawDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrentAccountMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "accountNumber", source = "accountNumber")
  @Mapping(target = "customerId", source = "customerId")
  @Mapping(target = "balance", source = "balance")
  @Mapping(target = "accountType", source = "accountType")
  @Mapping(target = "monthlyMovementLimit", source = "monthlyMovementLimit")
  @Mapping(target = "maintenanceCommission", source = "maintenanceCommission")
  @Mapping(target = "isActive", source = "isActive")
  CurrentAccount toCurrentAccount(CurrentAccountDocument currentAccountDocument);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "accountNumber", source = "accountNumber")
  @Mapping(target = "customerId", source = "customerId")
  @Mapping(target = "balance", source = "balance")
  @Mapping(target = "accountType", source = "accountType")
  @Mapping(target = "monthlyMovementLimit", source = "monthlyMovementLimit")
  @Mapping(target = "maintenanceCommission", source = "maintenanceCommission")
  @Mapping(target = "isActive", source = "isActive")
  CurrentAccountDocument toCurrentAccountDocument(CurrentAccount currentAccount);

  @Mapping(target = "id", source = "deposit.id")
  @Mapping(target = "accountId", source = "accountId")
  @Mapping(target = "amount", source = "deposit.amount")
  @Mapping(target = "depositDate", source = "deposit.depositDate")
  @Mapping(target = "reference", source = "deposit.reference")
  DepositDocument toDepositDocument(String accountId, Deposit deposit);

  WithdrawDocument toWithdrawDocument(Withdraw withdraw);

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
