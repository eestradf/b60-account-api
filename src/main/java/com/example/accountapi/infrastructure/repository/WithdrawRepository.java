package com.example.accountapi.infrastructure.repository;

import com.example.accountapi.infrastructure.repository.document.WithdrawDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WithdrawRepository extends ReactiveMongoRepository<WithdrawDocument, String> {
}
