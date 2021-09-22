package io.github.rafaelcarvalho.contas.infraestrutura.persistencia.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "conta_pagamento")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class ContaDePagamentoEntity {

  @Id
  @Include
  UUID id;
  @NotNull
  String nome;
  @NotNull
  BigDecimal valorOriginal;
  @NotNull
  BigDecimal valorCorrigido;
  @NotNull
  LocalDate pagamento;
  @NotNull
  LocalDate vencimento;
  @NotNull
  BigDecimal multaAplicada;
  @NotNull
  BigDecimal jurosAplicado;
  @NotNull
  Integer diasEmAtraso;

  @ManyToOne(optional = true)
  MultaEntity multa;
  @ManyToOne(optional = true)
  JurosEntity juros;

}
