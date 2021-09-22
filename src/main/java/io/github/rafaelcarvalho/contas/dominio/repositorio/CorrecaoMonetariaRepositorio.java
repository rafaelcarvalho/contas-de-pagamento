
package io.github.rafaelcarvalho.contas.dominio.repositorio;

import java.util.Optional;

import io.github.rafaelcarvalho.contas.dominio.vo.CorrecaoMonetaria;

public interface CorrecaoMonetariaRepositorio {

  Optional<CorrecaoMonetaria> recuperarCorrecaoMonetariaPorDiasEmAtraso(
      Integer diasEmAtraso);

}
