INSERT INTO ENERGY_PARAM   (CLIENT_TYPE,ENERGY_TYPE,FIRST_PRICE,SECOND_PRICE,UNIT) values ('PROFESSIONAL','GAZ', 0.117, 0.123, 'KWH'),
                                                                                          ('PROFESSIONAL','ELECTRICAL', 0.112, 0.110, 'KWH') ,
                                                                                          ('PARTICULAR','GAZ', 0.108, null, 'KWH'),
                                                                                          ('PARTICULAR','ELECTRICAL', 0.133, null, 'KWH');
INSERT INTO CLIENT (reference_ekwateur) values ('EKW154023987');
INSERT INTO CLIENT (reference_ekwateur) values ('EKW1540239877');
INSERT INTO CLIENT (reference_ekwateur) values ('EKW1540239879');
INSERT INTO PARTICULAR_CLIENT(CLIENT_ID, CIVILITY, FIRST_NAME, LAST_NAME) values (1,'M', 'PAUL','GOUD');
INSERT INTO PROFESSIONAL_CLIENT(CLIENT_ID,SIRET,SOCIAL_REASON,BUSINESS_FIGURES) values (2,'km12000', 'CAPIT','45000');
INSERT INTO PROFESSIONAL_CLIENT(CLIENT_ID,SIRET,SOCIAL_REASON,BUSINESS_FIGURES) values (3,'87545555', 'big','1000000');