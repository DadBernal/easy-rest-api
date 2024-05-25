/*
 * Copyright (c) 2024 Recurso de ejemplo creado por David Bernal.
 */

package com.dbernal.easy_rest_api.repository;

import com.dbernal.easy_rest_api.model.SubsidiaryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubsidiaryRepository extends CrudRepository<SubsidiaryEntity, Long> {

    List<SubsidiaryEntity> findAll();

    SubsidiaryEntity findByCode(String code);

    List<SubsidiaryEntity> findByNameContaining(String name);

}
