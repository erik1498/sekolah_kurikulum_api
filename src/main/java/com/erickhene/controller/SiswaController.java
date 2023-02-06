package com.erickhene.controller;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.Siswa;
import com.erickhene.service.impl.SiswaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/siswa/")
public class SiswaController {

    private final SiswaService siswaService;

    public SiswaController(SiswaService siswaService) {
        this.siswaService = siswaService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Siswa>>> getAll(){
        GlobalResponse<List<Siswa>> response = siswaService.getAll();
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
