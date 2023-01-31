package com.erickhene.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.KelasReq;
import com.erickhene.entity.impl.Kelas;
import com.erickhene.service.impl.KelasService;
import com.erickhene.util.ValidationUtil;

@RestController
@RequestMapping("/api/kelas/")
public class KelasController {

    final KelasService kelasService;
    
    @Autowired
    public KelasController(KelasService kelasService) {
        this.kelasService = kelasService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Kelas>>> getAll() {
        GlobalResponse<List<Kelas>> response = kelasService.getAll();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Kelas>> getByUUID(@PathVariable("uuid") String uuid){
        GlobalResponse<Kelas> response = kelasService.getByUuid(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody KelasReq kelasReq, Errors errors){
        if (errors.hasErrors()) {
            return ValidationUtil.generateError(errors);
        }
        Kelas kelas = kelasReq.convertToEntity();
        GlobalResponse<Kelas> response = kelasService.create(kelas);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<GlobalResponse<?>> update(@Valid @RequestBody KelasReq kelasReq, @PathVariable("uuid") String uuid, Errors errors){
        if (errors.hasErrors())
            return ValidationUtil.generateError(errors);
        Kelas kelas = kelasReq.convertToEntity();
        GlobalResponse<Kelas> response = kelasService.update(uuid, kelas);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Boolean>> delete(@PathVariable("uuid") String uuid){
        GlobalResponse<Boolean> response = kelasService.delete(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
