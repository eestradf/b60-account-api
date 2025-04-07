package com.example.accountapi.infrastructure.repository;

import com.example.accountapi.infrastructure.repository.document.CurrentAccountDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CurrentAccountRepository
    extends ReactiveMongoRepository<CurrentAccountDocument, String> {}
