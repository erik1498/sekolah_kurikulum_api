package com.erickhene.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.TingkatanKelasReq;
import com.erickhene.entity.impl.TingkatanKelas;
import com.erickhene.service.impl.TingkatanKelasService;
import com.erickhene.util.ValidationUtil;

@RestController
@RequestMapping("api/tingkatan_kelas/")
public class TingkatanKelasController {

    private final TingkatanKelasService tingkatanKelasService;

    public TingkatanKelasController(TingkatanKelasService tingkatanKelasService) {
        this.tingkatanKelasService = tingkatanKelasService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<?>> getAll() {
        GlobalResponse<List<TingkatanKelas>> response = tingkatanKelasService.getAll();    
        return ResponseEntity.status(response.code).body(response);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody TingkatanKelasReq tingkatanKelasReq, Errors errors) {
        if (errors.hasErrors()) {
            return ValidationUtil.generateError(errors);
        }
        GlobalResponse<TingkatanKelas> response = tingkatanKelasService.create(tingkatanKelasReq.convertToEntity());
        return ResponseEntity.status(response.code).body(response);
    }
}
