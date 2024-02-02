## Requirements
Java 21
## Jeux de test
une base mémoire est lancée avec des donnés de test (H2)
## Comment tester l'application
##### curl -d "energyType=ELECTRICAL&consumption=12500.12&unit=KWH"  -X POST http://localhost:8080/clients/EKW1540239879/calculated-consumption
