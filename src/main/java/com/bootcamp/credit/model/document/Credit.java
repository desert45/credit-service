package com.bootcamp.credit.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credits")
public class Credit {

  @Id
  private String documentNumber;
  private String typeCredit;
  private String paymentDay;
  private double amount;

  //
  // public Credit toModel(Credit credit) {
  //
  // return Credit.builder()
  // .documentNumber(credit.getDocumentNumber())
  // .typeCredit(credit.getTypeCredit())
  // .paymentDay(credit.getPaymentDay())
  // .amount(credit.getAmount())
  // .build();
  //
  //
  //
  // }
}
