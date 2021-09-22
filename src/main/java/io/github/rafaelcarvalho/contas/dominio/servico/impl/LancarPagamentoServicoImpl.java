package io.github.rafaelcarvalho.contas.dominio.servico.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import io.github.rafaelcarvalho.contas.dominio.entidade.ContaDePagamento;
import io.github.rafaelcarvalho.contas.dominio.repositorio.ContaDePagamentoRepositorio;
import io.github.rafaelcarvalho.contas.dominio.repositorio.CorrecaoMonetariaRepositorio;
import io.github.rafaelcarvalho.contas.dominio.servico.LancarPagamentoServico;
import io.github.rafaelcarvalho.contas.dominio.vo.CorrecaoMonetaria;

public class LancarPagamentoServicoImpl implements LancarPagamentoServico {

  private final ContaDePagamentoRepositorio contaDePagamentoRepositorio;
  private final CorrecaoMonetariaRepositorio correcaoMonetariaRepositorio;

  public LancarPagamentoServicoImpl(
      ContaDePagamentoRepositorio contaDePagamentoRepositorio,
      CorrecaoMonetariaRepositorio correcaoMonetariaRepositorio) {
    this.contaDePagamentoRepositorio = contaDePagamentoRepositorio;
    this.correcaoMonetariaRepositorio = correcaoMonetariaRepositorio;
  }

  @Override
  public UUID lancarPagamento(String nome, BigDecimal valorOriginal,
      LocalDate vencimento, LocalDate pagamento) {

    Integer diasEmAtraso = calcularDiasEmAtraso(vencimento, pagamento);
    CorrecaoMonetaria correcaoMonetaria = correcaoMonetariaRepositorio
        .recuperarCorrecaoMonetariaPorDiasEmAtraso(diasEmAtraso).orElse(null);
    ContaDePagamento contaDePagamento = new ContaDePagamento(nome,
        valorOriginal, pagamento, vencimento, correcaoMonetaria);

    return contaDePagamentoRepositorio.lancarPagamento(contaDePagamento);
  }

  private Integer calcularDiasEmAtraso(LocalDate vencimento,
      LocalDate pagamento) {

    if (pagamento.isBefore(vencimento) || pagamento.isEqual(vencimento)) {
      return 0;
    }

    return (int) ChronoUnit.DAYS.between(vencimento, pagamento);
  }

}
