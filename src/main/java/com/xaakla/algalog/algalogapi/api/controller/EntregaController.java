package com.xaakla.algalog.algalogapi.api.controller;

import com.xaakla.algalog.algalogapi.api.assembler.EntregaAssembler;
import com.xaakla.algalog.algalogapi.api.model.DestinatarioModel;
import com.xaakla.algalog.algalogapi.api.model.EntregaModel;
import com.xaakla.algalog.algalogapi.domain.model.Entrega;
import com.xaakla.algalog.algalogapi.domain.repository.EntregaRepository;
import com.xaakla.algalog.algalogapi.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody Entrega entrega) {
        return entregaAssembler.toModel(solicitacaoEntregaService.solicitar(entrega));
    }

    @GetMapping
    public List<EntregaModel> list() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> {
                    EntregaModel entregaModel = entregaAssembler.toModel(entrega);
//                    DestinatarioModel destinatarioModel = new DestinatarioModel(
//                            entrega.getDestinatario().getNome(),
//                            entrega.getDestinatario().getLogradouro(),
//                            entrega.getDestinatario().getNumero(),
//                            entrega.getDestinatario().getComplemento(),
//                            entrega.getDestinatario().getBairro()
//                    );
//                    EntregaModel entregaModel = new EntregaModel(
//                            entrega.getId(),
//                            entrega.getCliente().getName(),
//                            entrega.getDestinatario().getNome(),
//                            entrega.getDestinatario().getLogradouro(),
//                            entrega.getDestinatario().getNumero(),
//                            entrega.getDestinatario().getComplemento(),
//                            entrega.getDestinatario().getBairro(),
//                            entrega.getTaxa(),
//                            entrega.getStatus(),
//                            entrega.getDataPedido(),
//                            entrega.getDataFinalizacao()
//                    );

//                    entregaModel.setId(entrega.getId());
//                    entregaModel.setNomeCliente(entrega.getCliente().getName());
//                    entregaModel.setDestinatarioModel(new DestinatarioModel());
//                    entregaModel.getDestinatarioModel().setNome(entrega.getDestinatario().getNome());
//                    entregaModel.getDestinatarioModel().setLogradouro(entrega.getDestinatario().getLogradouro());
//                    entregaModel.getDestinatarioModel().setNumero(entrega.getDestinatario().getNumero());
//                    entregaModel.getDestinatarioModel().setComplemento(entrega.getDestinatario().getComplemento());
//                    entregaModel.getDestinatarioModel().setBairro(entrega.getDestinatario().getBairro());
//                    entregaModel.setTaxa(entrega.getTaxa());
//                    entregaModel.setStatus(entrega.getStatus());
//                    entregaModel.setDataPedido(entrega.getDataPedido());
//                    entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());


//                    return ResponseEntity.ok(
//                            EntregaModel.builder().id().nomeCliente().destinatarioModel().taxa().status().dataPedido().dataFinalizacao().build()
//                    );

                    return ResponseEntity.ok(entregaModel);
                }).orElse(ResponseEntity.notFound().build());
    }
}
