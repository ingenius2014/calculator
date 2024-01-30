package com.ekwateur.calculator.controller;

import com.ekwateur.calculator.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    ClientService service;


    @Test
    void should_calculated_consumption_client() throws Exception {

        // GIVEN
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("energyType", "ELECTRICAL");
        queryParams.set("consumption", "12500");
        queryParams.set("unit", "KWH");

        // GIVEN
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .post("/clients/EKW1540239879/calculated-consumption")
                .queryParams(queryParams)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        // THEN
        perform
                .andExpect(status().isCreated());

    }

}