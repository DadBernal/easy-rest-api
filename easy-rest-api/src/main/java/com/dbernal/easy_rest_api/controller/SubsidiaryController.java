/*
 * Copyright (c) 2024 Recurso de ejemplo creado por David Bernal.
 */

package com.dbernal.easy_rest_api.controller;

import com.dbernal.easy_rest_api.domain.Subsidiary;
import com.dbernal.easy_rest_api.model.SubsidiaryEntity;
import com.dbernal.easy_rest_api.service.SubsidiaryService;
import io.micrometer.observation.transport.ResponseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubsidiaryController {

    private final SubsidiaryService subsidiaryService;

    @Autowired
    public SubsidiaryController(SubsidiaryService subsidiaryService) {
        this.subsidiaryService = subsidiaryService;
    }

    /**
     * Get the list of subsidiaries in DB
     * @param name Name of the subsidiary
     * @return List of subsidiaries
     */
    @GetMapping("/subsidiaries")
    public ResponseEntity<List<SubsidiaryEntity>> getSubsidiaries(@RequestParam(required = false) String name) {
        List<SubsidiaryEntity> subsidiaries;
        if (name == null || name.isEmpty()) {
            subsidiaries = subsidiaryService.getSubsidiaries();
        } else {
            subsidiaries = subsidiaryService.getSubsidiaryByName(name);
        }

        return new ResponseEntity<>(
                subsidiaries, HttpStatus.OK);
    }

    /**
     * Get a Subsidiary by its Code
     * @param code the identify used BU
     * @return A Subsidiary detail
     */
    @GetMapping("/subsidiary/{code}")
    public ResponseEntity<SubsidiaryEntity> getSubsidiary(@PathVariable String code) {

        return new ResponseEntity<>(
                subsidiaryService.getSubsidiary(code), HttpStatus.OK);

    }


    /**
     * Create a new subsidiary and store it on  DB
     * @param subsidiary
     * @return the new subsidiary stored in DB
     */
    @PostMapping("/subsidiary")
    public ResponseEntity<SubsidiaryEntity> nwSubsidiary(@RequestBody Subsidiary subsidiary) {
        return new ResponseEntity<>(
                subsidiaryService.createSubsidiary(subsidiary), HttpStatus.CREATED);
    }

}
