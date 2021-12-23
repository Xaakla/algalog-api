package com.xaakla.algalog.algalogapi.domain.service;

import com.xaakla.algalog.algalogapi.domain.exception.NegocioException;
import com.xaakla.algalog.algalogapi.domain.model.Client;
import com.xaakla.algalog.algalogapi.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoClienteService {
    private ClientRepository clientRepository;

    @Transactional
    public Client salvar(Client cliente) {
        boolean isEmailUsing = clientRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (isEmailUsing) {
            throw new NegocioException("Já existe um cliente com este email");
        }

        return clientRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        clientRepository.deleteById(clienteId);
    }

    public Client buscar(Long clienteId){
        return clientRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente nao encontrado"));
    }
}
