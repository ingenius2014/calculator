package com.ekwateur.calculator.repository;

import com.ekwateur.calculator.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class ClientJpaRepositoryTest extends IntegrationTestBase {

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    @Test
    void should_find_client_by_reference() {
        // GIVEN
        String reference = "EKW154023987";

        // WHEN
        Optional<Client> client = clientJpaRepository.findClientByReferenceEkwateur(reference);

        // THEN
        Assertions.assertNotNull(client);
        Assertions.assertEquals(reference,client.orElseThrow().getReferenceEkwateur());
    }


}
