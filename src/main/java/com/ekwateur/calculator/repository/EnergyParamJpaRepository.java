package com.ekwateur.calculator.repository;

import com.ekwateur.calculator.model.EnergyParam;
import com.ekwateur.calculator.model.enums.ClientType;
import com.ekwateur.calculator.model.enums.EnergyType;
import com.ekwateur.calculator.model.enums.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnergyParamJpaRepository extends JpaRepository<EnergyParam, Long> {
    Optional<EnergyParam> findEnergyParamByClientTypeAndEnergyTypeAndUnit(ClientType clientType, EnergyType energyType, Unit unit);
}
