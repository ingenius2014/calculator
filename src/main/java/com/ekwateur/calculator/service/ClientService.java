package com.ekwateur.calculator.service;

import com.ekwateur.calculator.model.Client;
import com.ekwateur.calculator.model.EnergyParam;
import com.ekwateur.calculator.model.ParticularClient;
import com.ekwateur.calculator.model.ProfessionalClient;
import com.ekwateur.calculator.model.enums.ClientType;
import com.ekwateur.calculator.model.enums.EnergyType;
import com.ekwateur.calculator.model.enums.Unit;
import com.ekwateur.calculator.repository.ClientJpaRepository;
import com.ekwateur.calculator.repository.EnergyParamJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@Transactional
public class ClientService {


    private final ClientJpaRepository clientJpaRepository;
    private final EnergyParamJpaRepository energyParamJpaRepository;

    @Value("${client.bf.limit}")
    private BigDecimal businessFiguresLimit;

    public static final int SCALE_DECIMAL = 3;

    public ClientService(ClientJpaRepository clientJpaRepository, EnergyParamJpaRepository energyParamJpaRepository) {
        this.clientJpaRepository = clientJpaRepository;
        this.energyParamJpaRepository = energyParamJpaRepository;
    }

    @Transactional(readOnly = true)
    public BigDecimal calculatedConsumptionClient(String referenceEkwateur, double consumptionClient, EnergyType energyType, Unit unit) {

        log.info("Start calculate consumption client");

        Client client = clientJpaRepository.findClientByReferenceEkwateur(referenceEkwateur)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        return switch (client) {
            case ProfessionalClient professionalClient ->
                    launchCalculatedPriceForProfessional(professionalClient, consumptionClient, energyType, unit);
            case ParticularClient particularClient ->
                    launchCalculatedPriceForParticular(particularClient, consumptionClient, energyType, unit);
            default -> throw new IllegalStateException("Unexpected value: " + client);
        };
    }

    private BigDecimal launchCalculatedPriceForProfessional(ProfessionalClient client, double consumptionClient, EnergyType energyType, Unit unit) {

        log.info("Start calculate consumption for professional client with  reference {} and energy type {}", client.getReferenceEkwateur(), energyType.toString());

        EnergyParam energy = energyParamJpaRepository.findEnergyParamByClientTypeAndEnergyTypeAndUnit(ClientType.PROFESSIONAL, energyType, unit)
                .orElseThrow(() -> new IllegalArgumentException("Energy not found"));
        return client.isBusinessFiguresThanMoreLimit(businessFiguresLimit) ?
                energy.getSecondPrice().multiply(BigDecimal.valueOf(consumptionClient)).setScale(SCALE_DECIMAL, RoundingMode.CEILING) :
                energy.getFirstPrice().multiply(BigDecimal.valueOf(consumptionClient)).setScale(SCALE_DECIMAL, RoundingMode.CEILING);
    }


    private BigDecimal launchCalculatedPriceForParticular(ParticularClient client, double consumptionClient, EnergyType energyType, Unit unit) {

        log.info("Start calculate consumption for particular client with  reference {} and energy type {}", client.getReferenceEkwateur(), energyType.toString());

        EnergyParam energy = energyParamJpaRepository.findEnergyParamByClientTypeAndEnergyTypeAndUnit(ClientType.PARTICULAR, energyType, unit)
                .orElseThrow(() -> new IllegalArgumentException("Energy not found"));
        return energy.getFirstPrice().multiply(BigDecimal.valueOf(consumptionClient)).setScale(SCALE_DECIMAL, RoundingMode.CEILING);
    }
}
