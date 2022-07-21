package com.bootcamp.credit.service;

import com.bootcamp.credit.model.document.Credit;
import com.bootcamp.credit.model.repository.CreditRepository;
import com.bootcamp.credit.model.service.CreditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Validated
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Flux<Credit> getAll() {
        return this.creditRepository.findAll();
    }

    @Override
    public Mono<Credit> save(Credit credit) {
        return this.creditRepository.save(credit);
    }

    @Override
    public Mono<Credit> findById(String id) {
        return this.creditRepository.findById(id);
    }

    @Override
    public Mono<Boolean> existById(String id) {
        return this.creditRepository.existsById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.creditRepository.findById(id)
                .flatMap(existingCredit -> creditRepository.delete(existingCredit));
    }

    @Override
    public Mono<Credit> update(String id, Credit credit) {
        return this.creditRepository.findById(id)
                .flatMap(existingCredit-> creditRepository.save(credit));
    }
}
