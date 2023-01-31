package com.erickhene.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{uuid}")
    public ResponseEntity<GlobalResponse<MataPelajaran>> getByUuid(@PathVariable("uuid") String uuid){
        GlobalResponse<MataPelajaran> response = mataPelajaranService.getByUuid(uuid);
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

    @PutMapping("{uuid}")
    public ResponseEntity<GlobalResponse<?>> update(@Valid @RequestBody MataPelajaranReq mataPelajaranReq, @PathVariable("uuid") String uuid, Errors errors){
        if (errors.hasErrors())
            return ValidationUtil.generateError(errors);
        MataPelajaran mataPelajaran = mataPelajaranReq.convertToEntity();
        GlobalResponse<MataPelajaran> response = mataPelajaranService.update(uuid, mataPelajaran);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Boolean>> delete(@PathVariable("uuid") String uuid){
        GlobalResponse<Boolean> response = mataPelajaranService.delete(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
