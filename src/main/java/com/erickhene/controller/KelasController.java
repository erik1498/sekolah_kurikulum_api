package com.erickhene.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.Kelas;
import com.erickhene.service.impl.KelasService;

@RestController
@RequestMapping("/api/kelas/")
public class KelasController {

    final KelasService kelasService;
    
    public KelasController(KelasService kelasService) {
        this.kelasService = kelasService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<?>> getListKelas() {
        List<Kelas> allKelas = kelasService.getAllKelas();
        GlobalResponse<?> response = new GlobalResponse<List<Kelas>>("", HttpStatus.OK.value(), allKelas);
        return ResponseEntity.status(response.code).body(response);
    }
}
