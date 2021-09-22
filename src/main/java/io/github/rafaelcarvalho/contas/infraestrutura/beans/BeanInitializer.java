package io.github.rafaelcarvalho.contas.infraestrutura.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.rafaelcarvalho.contas.dominio.repositorio.ContaDePagamentoRepositorio;
import io.github.rafaelcarvalho.contas.dominio.repositorio.CorrecaoMonetariaRepositorio;
import io.github.rafaelcarvalho.contas.dominio.servico.LancarPagamentoServico;
import io.github.rafaelcarvalho.contas.dominio.servico.impl.LancarPagamentoServicoImpl;

@Configuration
public class BeanInitializer {

  @Bean
  public LancarPagamentoServico lancarPagamentoService(
      ContaDePagamentoRepositorio contaDePagamentoRepositorio,
      CorrecaoMonetariaRepositorio correcaoMonetariaRepositorio) {
    return new LancarPagamentoServicoImpl(contaDePagamentoRepositorio,
        correcaoMonetariaRepositorio);
  }

}
