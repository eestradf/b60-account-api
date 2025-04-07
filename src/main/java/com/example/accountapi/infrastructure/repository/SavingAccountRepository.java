package com.example.accountapi.infrastructure.repository;

import com.example.accountapi.infrastructure.repository.document.SavingAccountDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SavingAccountRepository
    extends ReactiveMongoRepository<SavingAccountDocument, String> {}
