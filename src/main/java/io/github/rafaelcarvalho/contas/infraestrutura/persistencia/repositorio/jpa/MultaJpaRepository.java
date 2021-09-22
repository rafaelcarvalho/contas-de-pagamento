package io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.entidade.MultaEntity;

public interface MultaJpaRepository extends CrudRepository<MultaEntity, UUID> {

  @Query("FROM MultaEntity m WHERE m.diasEmAtrasoInicial <= :diasEmAtraso And m.diasEmAtrasoFinal >= :diasEmAtraso")
  Optional<MultaEntity> recuperarMultaPorDiasEmAtraso(
      @Param("diasEmAtraso") Integer diasEmAtraso);

}
