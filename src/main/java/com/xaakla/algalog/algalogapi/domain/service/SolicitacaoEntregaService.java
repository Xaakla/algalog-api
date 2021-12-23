package com.xaakla.algalog.algalogapi.domain.service;

import com.xaakla.algalog.algalogapi.domain.exception.NegocioException;
import com.xaakla.algalog.algalogapi.domain.model.Client;
import com.xaakla.algalog.algalogapi.domain.model.Entrega;
import com.xaakla.algalog.algalogapi.domain.model.StatusEntrega;
import com.xaakla.algalog.algalogapi.domain.repository.ClientRepository;
import com.xaakla.algalog.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Client cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
