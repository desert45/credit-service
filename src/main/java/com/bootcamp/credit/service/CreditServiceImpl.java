package com.bootcamp.credit.service;

import com.bootcamp.credit.dto.CustomerDto;
import com.bootcamp.credit.model.document.Credit;
import com.bootcamp.credit.model.repository.CreditRepository;
import com.bootcamp.credit.model.service.CreditService;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import utils.WebClientUtilsService;

@Service
@Slf4j
@Validated
public class CreditServiceImpl implements CreditService {

  @Autowired
  private CreditRepository creditRepository;

  private WebClientUtilsService webClientUtilsService;

  // private WebClient webClient;

  // WebClient webClient =
  // WebClient.builder().baseUrl("http://localhost:8082").build();

  WebClient webClient;

  @PostConstruct
  public void init() {
    webClient = WebClient.builder().baseUrl("http://localhost:8080/")
        .defaultHeader(HttpHeaders.CONTENT_TYPE,
            MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Override
  public Flux<Credit> getAll() {
    return this.creditRepository.findAll();
  }

  // @Override
  // public Mono<Credit> save(Credit credit) {
  //
  // return flatMap(creditf ->
  // WebClientUtilsService.getCustomerByDni(credit.getDocumentNumber())
  // .switchIfEmpty(Mono.error(new CustomerNotFoundException("Customer dont
  // Fount: --- " + credit.getDocumentNumber())))
  // .doOnError(ex -> log.error("Client not found" +
  // creditCardRequest1.getClient()))
  // .
  //
  // );
  //
  // // return this.creditRepository.save(credit);
  // }

  /*
   * @Override public Mono<Credit> findById(String documentNumber) { return
   * this.creditRepository.findById(documentNumber); }
   */
  // public Mono<CreditCardResponse> saveCreditCard(CreditCardRequest
  // creditCardRequest) {
  // return Mono.just(creditCardRequest)
  // .map(CreditCardRequest::toModel)
  // .flatMap(creditCardRequest1 ->
  // webClientUtils.getClient(creditCardRequest1.getClient())
  // .switchIfEmpty(Mono.error(new CreditNotFoundException("Client not found: "
  // + creditCardRequest1.getClient())))
  // .doOnError(ex -> log.error("Client not found" +
  // creditCardRequest1.getClient()))
  // .flatMap(res -> repository.save(creditCardRequest1))
  // )
  // .map(CreditCardResponse::fromModel);
  //
  // }

  @Override
  public Mono<Credit> findById(String documentNumber) {

    // Mono<CustomerDto> customerMono = webClient.get()
    // .uri("credits" + documentNumber)
    // .retrieve()
    // .bodyToMono(CustomerDto.class);
    //
    // customerMono.map( f -> f.getFirstName()
    // .concat(f.getLastName())
    // .toLowerCase());

    return this.creditRepository.findById(documentNumber);

  }

  @Override
  public Mono<Boolean> existById(String documentNumber) {
    return this.creditRepository.existsById(documentNumber);
  }

  @Override
  public Mono<Void> delete(String documentNumber) {
    return this.creditRepository.findById(documentNumber)
        .flatMap(existingCredit -> creditRepository.delete(existingCredit));
  }

  @Override
  public Mono<Credit> update(String documentNumber, Credit credit) {
    return this.creditRepository.findById(documentNumber)
        .flatMap(existingCredit -> creditRepository.save(credit));
  }

  // WebClientUtilsService.getCustomerByDni(credit.getDocumentNumber()

  @Override
  public Mono<Credit> save(Credit credit) {

    webClient.get().uri("/customers/" + credit.getDocumentNumber())
        // .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(CustomerDto.class).subscribe(custom -> {
          System.out.println("People::::::----- ");
          System.out.println(custom.getFirstName());
          log.info(" ---- Customers ------ : " + custom.getLastName());
        });

    return this.creditRepository.save(credit);

  }

}
