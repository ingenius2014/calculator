package com.ekwateur.calculator.model;

import com.ekwateur.calculator.model.enums.ClientType;
import com.ekwateur.calculator.model.enums.EnergyType;
import com.ekwateur.calculator.model.enums.Unit;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "ENERGY_PARAM")
@Entity
public class EnergyParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENERGY_TYPE")
    private EnergyType energyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLIENT_TYPE")
    private ClientType clientType;

    @Column(name = "FIRST_PRICE", precision = 4, scale = 3)
    private BigDecimal firstPrice;

    @Column(name = "SECOND_PRICE", precision = 4, scale = 3)
    private BigDecimal secondPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "UNIT")
    private Unit unit;

}
