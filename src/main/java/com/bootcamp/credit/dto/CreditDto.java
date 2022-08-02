package com.bootcamp.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {

  private String documentNumber;
  private String typeCredit;
  private String paymentDay;
  private double amount;

  // public CreditDto toModel(Credit credit) {
  //
  // return CreditDto.builder()
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
