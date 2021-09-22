package io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.entidade.JurosEntity;

public interface JurosJpaRepository extends CrudRepository<JurosEntity, UUID> {

  @Query("FROM JurosEntity j WHERE j.diasEmAtrasoInicial <= :diasEmAtraso And j.diasEmAtrasoFinal >= :diasEmAtraso")
  Optional<JurosEntity> recuperarJurosPorDiasEmAtraso(
      @Param("diasEmAtraso") Integer diasEmAtraso);

}