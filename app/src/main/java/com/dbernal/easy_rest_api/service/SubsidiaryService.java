/*
 * Copyright (c) 2024 Recurso de ejemplo creado por David Bernal.
 */

package com.dbernal.easy_rest_api.service;

import com.dbernal.easy_rest_api.domain.Subsidiary;
import com.dbernal.easy_rest_api.model.SubsidiaryEntity;
import com.dbernal.easy_rest_api.repository.SubsidiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsidiaryService {

    private final SubsidiaryRepository subsidiaryRepository;

    @Autowired
    public SubsidiaryService(SubsidiaryRepository subsidiaryRepository) {
        this.subsidiaryRepository = subsidiaryRepository;
    }

    public List<SubsidiaryEntity> getSubsidiaries() {
       return subsidiaryRepository.findAll();
    }

    public SubsidiaryEntity getSubsidiary(String code) {
        return subsidiaryRepository.findByCode(code);
    }

    public List<SubsidiaryEntity> getSubsidiaryByName(String name) {
        return subsidiaryRepository.findByNameContaining(name);
    }

    public SubsidiaryEntity createSubsidiary(Subsidiary subsidiary) {
        SubsidiaryEntity newSubsidiary = new SubsidiaryEntity();
        newSubsidiary.setAddress(subsidiary.getAddress());
        newSubsidiary.setManager(subsidiary.getManager());
        newSubsidiary.setCountry(subsidiary.getCountry());
        newSubsidiary.setCode(subsidiary.getCode());
        newSubsidiary.setPhoneNumber(subsidiary.getPhoneNumber());
        newSubsidiary.setName(subsidiary.getName());

        return subsidiaryRepository.save(newSubsidiary);
    }

}
