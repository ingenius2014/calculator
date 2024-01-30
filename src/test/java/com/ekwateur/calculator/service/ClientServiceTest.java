package com.ekwateur.calculator.service;

import com.ekwateur.calculator.model.enums.EnergyType;
import com.ekwateur.calculator.model.enums.Unit;
import com.ekwateur.calculator.repository.ClientJpaRepository;
import com.ekwateur.calculator.repository.EnergyParamJpaRepository;
import com.ekwateur.calculator.repository.IntegrationTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

class ClientServiceTest extends IntegrationTestBase {

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    @Autowired
    private EnergyParamJpaRepository energyParamJpaRepository;

    ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clientJpaRepository, energyParamJpaRepository);
        ReflectionTestUtils.setField(clientService, "businessFiguresLimit", BigDecimal.valueOf(1000000));

    }

    @DisplayName("get consumption for different cases ")
    @ParameterizedTest(name = "get consumption client")
    @MethodSource("applyValueWithParam")
    void should_calculated_consumption_client(String reference, double consumption, EnergyType energyType, BigDecimal out) {
        BigDecimal consumptionClient = clientService.calculatedConsumptionClient(reference, consumption, energyType, Unit.KWH);
        Assertions.assertEquals(out, consumptionClient);
    }

    private static Stream<Arguments> applyValueWithParam() {
        return Stream.of(
                Arguments.of("EKW154023987", 10, EnergyType.ELECTRICAL, BigDecimal.valueOf(1.330).setScale(3, RoundingMode.CEILING)), // particular
                Arguments.of("EKW1540239877", 10, EnergyType.GAZ, BigDecimal.valueOf(1.170).setScale(3, RoundingMode.CEILING)),// Professional  less than limit businessFigures
                Arguments.of("EKW1540239879", 10, EnergyType.ELECTRICAL, BigDecimal.valueOf(1.100).setScale(3, RoundingMode.CEILING)) // Professional  more  than limit businessFigures
        );
    }


    @Test
    void catch_exception_when_not_reference_ekwateur() {
        // given
        String reference = "154023987";

        // when then
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                clientService.calculatedConsumptionClient(reference, 10, EnergyType.ELECTRICAL, Unit.KWH));
    }
}