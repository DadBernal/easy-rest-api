/*
 * Copyright (c) 2024 Recurso de ejemplo creado por David Bernal.
 */

package com.dbernal.easy_rest_api.controller;

import com.dbernal.easy_rest_api.domain.Subsidiary;
import com.dbernal.easy_rest_api.model.SubsidiaryEntity;
import com.dbernal.easy_rest_api.repository.SubsidiaryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SubsidiaryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SubsidiaryRepository subsidiaryRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void init() {
        createdSubsidiaryData();
    }

    @AfterEach
    void teardown() {
        deleteSubsidiaries();
    }

    @Test
    public void whenGetSubsidiaries_thenResturnOk() throws Exception {
        mockMvc.perform(get("/subsidiaries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("ADF1321"))
                .andExpect(jsonPath("$[0].country").value("USA"))
                .andExpect(jsonPath("$[0].name").value("Florida 1"));

    }

    @Test
    public void whenGetSubsidiaryByCode_thenResturnOk() throws Exception {
        MvcResult result = mockMvc.perform(get("/subsidiary/ADF1321"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("ADF1321"))
                .andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.name").value("Florida 1")).andReturn();
        result.getResponse();

    }

    @Test
    public void whenGetSubsidiariesByName_thenResturnOk() throws Exception {
        mockMvc.perform(get("/subsidiaries?name=Florida"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        mockMvc.perform(get("/subsidiaries?name=Atlanta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void whenCreateSubsidiaries_thenResturnCreated() throws Exception {
        Subsidiary newSubsidiary = new Subsidiary();
        newSubsidiary.setName("California 1");
        newSubsidiary.setCode("ADF1357");
        newSubsidiary.setManager("Camilo Muñoz");
        newSubsidiary.setPhoneNumber("+1 1124526325");
        newSubsidiary.setCountry("USA");

        String subsidiaryJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newSubsidiary);
        mockMvc.perform(post("/subsidiary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(subsidiaryJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("ADF1357"))
                .andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.name").value("California 1"));

    }

    private void createdSubsidiaryData()  {
        SubsidiaryEntity subsidiaryEntity1 = new SubsidiaryEntity();
        subsidiaryEntity1.setCode("ADF1321");
        subsidiaryEntity1.setName("Florida 1");
        subsidiaryEntity1.setCountry("USA");
        subsidiaryEntity1.setPhoneNumber("+1 1245622655");
        subsidiaryEntity1.setManager("Juan Perez");
        subsidiaryEntity1.setAddress("Av Florida 1212, Florida");
        subsidiaryRepository.save(subsidiaryEntity1);

        SubsidiaryEntity subsidiaryEntity2 = new SubsidiaryEntity();
        subsidiaryEntity2.setCode("ADF1322");
        subsidiaryEntity2.setName("Florida 2");
        subsidiaryEntity2.setCountry("USA");
        subsidiaryEntity2.setPhoneNumber("+1 124562343655");
        subsidiaryEntity2.setManager("Maria Gonzalez");
        subsidiaryEntity2.setAddress("Av Florida 3412, Florida");
        subsidiaryRepository.save(subsidiaryEntity2);

        SubsidiaryEntity subsidiaryEntity3 = new SubsidiaryEntity();
        subsidiaryEntity3.setCode("ADF1323");
        subsidiaryEntity3.setName("Atlanta 1");
        subsidiaryEntity3.setCountry("USA");
        subsidiaryEntity3.setPhoneNumber("+1 232562343665");
        subsidiaryEntity3.setManager("Pedro Muñoz");
        subsidiaryEntity3.setAddress("Av Georgia 812, Atlanta");
        subsidiaryRepository.save(subsidiaryEntity3);

    }

    private void deleteSubsidiaries() {
       subsidiaryRepository.deleteAll();
    }
}
