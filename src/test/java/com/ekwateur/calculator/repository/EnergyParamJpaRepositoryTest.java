package com.ekwateur.calculator.repository;

import com.ekwateur.calculator.model.EnergyParam;
import com.ekwateur.calculator.model.enums.ClientType;
import com.ekwateur.calculator.model.enums.EnergyType;
import com.ekwateur.calculator.model.enums.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

class EnergyParamJpaRepositoryTest extends IntegrationTestBase {

    @Autowired
    private EnergyParamJpaRepository repository;

    @Test
    void should_find_energy_by_criteria() {
        // GIVEN

        ClientType clientType = ClientType.PARTICULAR;
        EnergyType gaz = EnergyType.GAZ;
        Unit unit = Unit.KWH;

        // WHEN

        Optional<EnergyParam> energy = repository.findEnergyParamByClientTypeAndEnergyTypeAndUnit(clientType, gaz, unit);

        // THEN
        Assertions.assertNotNull(energy);
        Assertions.assertEquals(BigDecimal.valueOf(0.108),energy.orElseThrow().getFirstPrice());
        Assertions.assertNull(energy.orElseThrow().getSecondPrice());
    }


}
