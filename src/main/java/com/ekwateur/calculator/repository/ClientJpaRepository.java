package com.ekwateur.calculator.repository;

import com.ekwateur.calculator.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByReferenceEkwateur(String reference);
}
