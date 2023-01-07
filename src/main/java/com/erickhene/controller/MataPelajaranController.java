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
import com.erickhene.dto.request.MataPelajaranReq;
import com.erickhene.entity.impl.MataPelajaran;
import com.erickhene.service.impl.MataPelajaranService;
import com.erickhene.util.ValidationUtil;

@RestController
@RequestMapping("/api/mata_pelajaran/")
public class MataPelajaranController {
    final MataPelajaranService mataPelajaranService;
    
    @Autowired
    public MataPelajaranController(MataPelajaranService mataPelajaranService) {
        this.mataPelajaranService = mataPelajaranService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<MataPelajaran>>> getAll(){
        GlobalResponse<List<MataPelajaran>> response = mataPelajaranService.getAll();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody MataPelajaranReq req, Errors errors){
        if (errors.hasErrors()) {
            return ValidationUtil.generateError(errors);
        }
        GlobalResponse<MataPelajaran> response = mataPelajaranService.create(req.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
