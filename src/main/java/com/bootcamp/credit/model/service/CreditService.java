package com.bootcamp.credit.model.service;

import com.bootcamp.credit.model.document.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService  {

    Flux<Credit> getAll();
    Mono<Credit> save(Credit credit);

    Mono<Credit> findById(String id);
    Mono<Boolean> existById(String id);

    Mono<Void> delete(String id);

    Mono<Credit> update(String id, Credit credit);
}
