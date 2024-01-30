package com.ekwateur.calculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "PROFESSIONAL_CLIENT")
@PrimaryKeyJoinColumn(name = "CLIENT_ID")
public class ProfessionalClient extends Client implements Serializable {

    private String siret;

    @Column(name = "SOCIAL_REASON")
    private String socialReason;

    @Column(name = "BUSINESS_FIGURES", scale = 3, nullable = false)
    private BigDecimal businessFigures;

    public boolean isBusinessFiguresThanMoreLimit(BigDecimal limit) {
        return this.businessFigures.compareTo(limit) >= 0;
    }
}
