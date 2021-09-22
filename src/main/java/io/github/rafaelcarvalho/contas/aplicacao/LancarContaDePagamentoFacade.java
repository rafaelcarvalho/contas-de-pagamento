package io.github.rafaelcarvalho.contas.aplicacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface LancarContaDePagamentoFacade {

  UUID lancarPagamento(String nome, BigDecimal valor, LocalDate vencimento, LocalDate pagamento);

}
