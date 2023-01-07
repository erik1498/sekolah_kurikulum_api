package com.erickhene.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.KelassReq;
import com.erickhene.entity.impl.Kelas;
import com.erickhene.service.impl.KelassService;
import com.erickhene.util.ValidationUtil;

@RestController
@RequestMapping("/api/kelas/")
public class KelassController {

    final KelassService kelasService;
    
    @Autowired
    public KelassController(KelassService kelasService) {
        this.kelasService = kelasService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<?>> getAll() {
        GlobalResponse<List<Kelas>> response = kelasService.getAll();
        return ResponseEntity.status(response.code).body(response);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody KelassReq kelasReq, Errors errors){
        if (errors.hasErrors()) {
            return ValidationUtil.generateError(errors);
        }
        Kelas kelas = kelasReq.convertToEntity();
        GlobalResponse<Kelas> response = kelasService.create(kelas);
        return ResponseEntity.status(response.code).body(response);
    }
}
