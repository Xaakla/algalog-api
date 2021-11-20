package com.xaakla.algalog.algalogapi.domain.repository;

import com.xaakla.algalog.algalogapi.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByName(String name);
    List<Client> findByNameContaining(String name);
}