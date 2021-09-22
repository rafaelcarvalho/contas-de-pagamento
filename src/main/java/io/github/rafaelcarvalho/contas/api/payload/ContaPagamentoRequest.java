package io.github.rafaelcarvalho.contas.api.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Value;

@Value
public class ContaPagamentoRequest {

  String nome;
  BigDecimal valor;
  LocalDate vencimento;
  LocalDate pagamento;

}
