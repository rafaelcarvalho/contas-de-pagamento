package io.github.rafaelcarvalho.contas.dominio.servico;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface LancarPagamentoServico {

  UUID lancarPagamento(String nome, BigDecimal valorOriginal,
      LocalDate vencimento, LocalDate pagamento);

}
