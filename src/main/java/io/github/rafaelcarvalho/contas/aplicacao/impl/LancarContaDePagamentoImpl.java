package io.github.rafaelcarvalho.contas.aplicacao.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.rafaelcarvalho.contas.aplicacao.LancarContaDePagamentoFacade;
import io.github.rafaelcarvalho.contas.dominio.servico.LancarPagamentoServico;

@Service
@Transactional
class LancarContaDePagamentoImpl implements LancarContaDePagamentoFacade {

  private final LancarPagamentoServico servico;

  public LancarContaDePagamentoImpl(LancarPagamentoServico servico) {
    this.servico = servico;
  }

  public UUID lancarPagamento(String nome, BigDecimal valor, LocalDate vencimento, LocalDate pagamento) {
    return servico.lancarPagamento(nome, valor, vencimento, pagamento);
  }

}
