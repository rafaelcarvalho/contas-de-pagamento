package io.github.rafaelcarvalho.contas.dominio.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.github.rafaelcarvalho.contas.dominio.vo.CorrecaoMonetaria;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ContaDePagamento {

  private UUID id;
  private String nome;
  private BigDecimal valorOriginal;
  private BigDecimal valorCorrigido;
  private BigDecimal multa;
  private BigDecimal juros;
  private LocalDate pagamento;
  private LocalDate vencimento;
  private CorrecaoMonetaria correcaoMonetaria;

  public ContaDePagamento(String nome, BigDecimal valorOriginal,
      LocalDate pagamento, LocalDate vencimento,
      CorrecaoMonetaria correcaoMonetaria) {
    this.id = UUID.randomUUID();
    this.nome = nome;
    this.valorOriginal = valorOriginal;
    this.pagamento = pagamento;
    this.vencimento = vencimento;
    this.correcaoMonetaria = correcaoMonetaria;
    this.valorCorrigido = calcularCorrecao();
  }

  private BigDecimal calcularCorrecao() {

    if (correcaoMonetaria == null) {
      this.multa = BigDecimal.ZERO;
      this.juros = BigDecimal.ZERO;
      return valorOriginal.multiply(new BigDecimal(1));
    }

    this.multa = valorOriginal
        .multiply(correcaoMonetaria.getMulta().divide(new BigDecimal(100)));
    this.juros = valorOriginal
        .multiply(correcaoMonetaria.getJuros().divide(new BigDecimal(100))
            .multiply(new BigDecimal(correcaoMonetaria.getDiasEmAtraso())));
    return valorOriginal.add(juros).add(multa);
  }

}
