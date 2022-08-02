package com.bootcamp.credit.model.repository;

import com.bootcamp.credit.model.document.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository
    extends
      ReactiveMongoRepository<Credit, String> {

}
