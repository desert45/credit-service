package com.bootcamp.credit.api;

import com.bootcamp.credit.model.document.Credit;
import com.bootcamp.credit.model.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping
    public Mono<Credit> register(@RequestBody Credit credit){
        return creditService.save(credit);
    }

    @GetMapping
    public Flux<Credit> getAllCredits(){
        return creditService.getAll();
    }

    @GetMapping("{id}")
    public Mono<Credit> getCreditById(@PathVariable String id){
        return creditService.findById(id);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id){
        return creditService.delete(id);
    }

    @PutMapping("{id}")
    public Mono<Credit> updateCredit(@PathVariable String id, @RequestBody Credit credit){
        return  creditService.update(id,credit);
    }

}
