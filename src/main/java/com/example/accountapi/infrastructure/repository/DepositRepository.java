package com.example.accountapi.infrastructure.repository;

import com.example.accountapi.infrastructure.repository.document.DepositDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DepositRepository extends ReactiveMongoRepository<DepositDocument, String> {}
