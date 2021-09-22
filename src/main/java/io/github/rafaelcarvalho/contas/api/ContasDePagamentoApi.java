package io.github.rafaelcarvalho.contas.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rafaelcarvalho.contas.api.payload.ContaPagamentoRequest;
import io.github.rafaelcarvalho.contas.aplicacao.LancarContaDePagamentoFacade;

@RestController
@RequestMapping("/api/contta-de-pagamento'")
public class ContasDePagamentoApi {
  
  private final LancarContaDePagamentoFacade facade;

  public ContasDePagamentoApi(LancarContaDePagamentoFacade facade) {
    this.facade = facade;
  }
  
  @PostMapping
  public ResponseEntity<String> lancarPagamento(@RequestBody ContaPagamentoRequest request) {
    UUID id = facade.lancarPagamento(request.getNome(), request.getValor(), request.getVencimento(), request.getPagamento());
    return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
  }

}
