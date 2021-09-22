package io.github.rafaelcarvalho.contas.infraestrutura.persistencia.repositorio.jpa;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.rafaelcarvalho.contas.infraestrutura.persistencia.entidade.ContaDePagamentoEntity;

public interface ContaDePagamentoJpaRepository extends PagingAndSortingRepository<ContaDePagamentoEntity, UUID> {

}
