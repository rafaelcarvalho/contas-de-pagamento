package io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.rafaelcarvalho.contas.dominio.entidade.ContaDePagamento;
import io.github.rafaelcarvalho.contas.dominio.repositorio.ContaDePagamentoRepositorio;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.entidade.ContaDePagamentoEntity;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.entidade.ContaDePagamentoEntity.ContaDePagamentoEntityBuilder;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.jpa.ContaDePagamentoJpaRepository;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.jpa.JurosJpaRepository;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.jpa.MultaJpaRepository;

@Service
public class ContaDePagamentoRepositorioImpl implements ContaDePagamentoRepositorio {
  
  private final ContaDePagamentoJpaRepository contaDePagamentoRepository;
  private final MultaJpaRepository multaRepository;
  private final JurosJpaRepository jurosRepository;

  public ContaDePagamentoRepositorioImpl(
      ContaDePagamentoJpaRepository contaDePagamentoRepository,
      MultaJpaRepository multaRepository, JurosJpaRepository jurosRepository) {
    this.contaDePagamentoRepository = contaDePagamentoRepository;
    this.multaRepository = multaRepository;
    this.jurosRepository = jurosRepository;
  }


  @Override
  public UUID lancarPagamento(ContaDePagamento pagamento) {
    ContaDePagamentoEntityBuilder builder = ContaDePagamentoEntity.builder()
        .id(pagamento.getId())
        .diasEmAtraso(pagamento.getCorrecaoMonetaria().getDiasEmAtraso())
        .nome(pagamento.getNome())
        .pagamento(pagamento.getPagamento())
        .vencimento(pagamento.getVencimento())
        .valorCorrigido(pagamento.getValorCorrigido())
        .valorOriginal(pagamento.getValorOriginal())
        .jurosAplicado(pagamento.getJuros())
        .multaAplicada(pagamento.getMulta());
    if (pagamento.getCorrecaoMonetaria().getMultaId() != null) {
      builder.multa(multaRepository.findById(pagamento.getCorrecaoMonetaria().getMultaId()).orElse(null));
    }
    if (pagamento.getCorrecaoMonetaria().getJurosId() != null) {
      builder.juros(jurosRepository.findById(pagamento.getCorrecaoMonetaria().getJurosId()).orElse(null));
    }
    
    return contaDePagamentoRepository.save(builder.build()).getId();
  }

}
