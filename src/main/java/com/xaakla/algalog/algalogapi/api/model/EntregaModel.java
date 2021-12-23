package com.xaakla.algalog.algalogapi.api.model;

import com.xaakla.algalog.algalogapi.domain.model.StatusEntrega;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaModel {
    private Long id;
    private String nomeCliente;
    private DestinatarioModel destinatarioModel;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
