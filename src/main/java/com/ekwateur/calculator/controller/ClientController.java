package com.ekwateur.calculator.controller;

import com.ekwateur.calculator.model.enums.EnergyType;
import com.ekwateur.calculator.model.enums.Unit;
import com.ekwateur.calculator.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/{referenceEkwateur:^EKW.*$}/calculated-consumption")
    public ResponseEntity<BigDecimal> calculatedConsumptionClient(@PathVariable String referenceEkwateur, @RequestParam EnergyType energyType,
                                                                  @RequestParam double consumption, @RequestParam Unit unit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.calculatedConsumptionClient(referenceEkwateur, consumption, energyType, unit));
    }


}
