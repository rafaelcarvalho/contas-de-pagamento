package io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.rafaelcarvalho.contas.dominio.repositorio.CorrecaoMonetariaRepositorio;
import io.github.rafaelcarvalho.contas.dominio.vo.CorrecaoMonetaria;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.entidade.JurosEntity;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.entidade.MultaEntity;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.jpa.JurosJpaRepository;
import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.jpa.MultaJpaRepository;

@Service
public class CorrecaoMonetariaRepositorioImpl
    implements
      CorrecaoMonetariaRepositorio {

  private final MultaJpaRepository multaRepository;
  private final JurosJpaRepository jurosRepository;

  public CorrecaoMonetariaRepositorioImpl(MultaJpaRepository multaRepository,
      JurosJpaRepository jurosRepository) {
    this.multaRepository = multaRepository;
    this.jurosRepository = jurosRepository;
  }

  @Override
  public Optional<CorrecaoMonetaria> recuperarCorrecaoMonetariaPorDiasEmAtraso(
      Integer diasEmAtraso) {

    Optional<JurosEntity> juros = jurosRepository
        .recuperarJurosPorDiasEmAtraso(diasEmAtraso);
    Optional<MultaEntity> multa = multaRepository
        .recuperarMultaPorDiasEmAtraso(diasEmAtraso);

    BigDecimal multaASerAplicada = BigDecimal.ZERO;
    BigDecimal jurosASerAplicado = BigDecimal.ZERO;
    UUID multaId = null;
    UUID jurosId = null;
    
    if (multa.isPresent()) {
      multaASerAplicada = multa.get().getValor();
      multaId = multa.get().getId();
    }
    if (juros.isPresent()) {
      jurosASerAplicado = juros.get().getValor();
      jurosId = juros.get().getId();
    }

    return Optional.ofNullable(new CorrecaoMonetaria(multaId, jurosId,
        diasEmAtraso, multaASerAplicada, jurosASerAplicado));
  }

}
