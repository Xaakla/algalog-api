package com.xaakla.algalog.algalogapi.api.controller;

import com.xaakla.algalog.algalogapi.domain.model.Client;
import com.xaakla.algalog.algalogapi.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> listClient(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) return ResponseEntity.ok(client.get());

        return ResponseEntity.status(404).body(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveClient(@RequestBody @Valid Client client) {
        clientRepository.save(client);
        return client.getId();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> alterClient(@PathVariable Long id, @RequestBody @Valid Client client) {
        if (!clientRepository.existsById(id))
            return ResponseEntity.notFound().build();

        client.setId(id);
        clientRepository.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (!clientRepository.existsById(id))
            return ResponseEntity.notFound().build();

        clientRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}