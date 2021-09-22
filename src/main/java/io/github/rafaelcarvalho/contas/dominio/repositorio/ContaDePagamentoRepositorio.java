package io.github.rafaelcarvalho.contas.dominio.repositorio;

import java.util.UUID;

import io.github.rafaelcarvalho.contas.dominio.entidade.ContaDePagamento;

public interface ContaDePagamentoRepositorio {

  UUID lancarPagamento(ContaDePagamento pagamento);

}
