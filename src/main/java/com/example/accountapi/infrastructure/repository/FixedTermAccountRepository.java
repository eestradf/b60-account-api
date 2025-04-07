package com.example.accountapi.infrastructure.repository;

import com.example.accountapi.infrastructure.repository.document.FixedTermAccountDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FixedTermAccountRepository
    extends ReactiveMongoRepository<FixedTermAccountDocument, String> {}
