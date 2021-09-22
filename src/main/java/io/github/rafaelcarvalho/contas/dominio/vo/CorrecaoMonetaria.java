package io.github.rafaelcarvalho.contas.dominio.vo;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Value;

@Value
public class CorrecaoMonetaria {

  UUID multaId;
  UUID jurosId;
  Integer diasEmAtraso;
  BigDecimal multa;
  BigDecimal juros;

}
