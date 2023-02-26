package com.erickhene.controller;

import java.util.List;

import javax.validation.Valid;

import com.erickhene.dto.request.DataTableReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.TingkatanKelasReq;
import com.erickhene.entity.impl.TingkatanKelas;
import com.erickhene.service.impl.TingkatanKelasService;
import com.erickhene.util.ValidationUtil;

@RestController
@RequestMapping("api/tingkatan_kelas/")
public class TingkatanKelasController {

    final TingkatanKelasService tingkatanKelasService;

    @Autowired
    public TingkatanKelasController(TingkatanKelasService tingkatanKelasService) {
        this.tingkatanKelasService = tingkatanKelasService;
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<List<TingkatanKelas>>> getAll(@RequestBody DataTableReq dataTableReq) {
        GlobalResponse<List<TingkatanKelas>> response = tingkatanKelasService.getAll(dataTableReq);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<GlobalResponse<TingkatanKelas>> getByUuid(@PathVariable("uuid") String uuid){
        GlobalResponse<TingkatanKelas> response = tingkatanKelasService.getByUuid(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody TingkatanKelasReq tingkatanKelasReq, Errors errors) {
        if (errors.hasErrors()) {
            return ValidationUtil.generateError(errors);
        }
        GlobalResponse<TingkatanKelas> response = tingkatanKelasService.create(tingkatanKelasReq.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<GlobalResponse<?>> update(@Valid @RequestBody TingkatanKelasReq tingkatanKelasReq, @PathVariable("uuid") String uuid, Errors errors){
        if (errors.hasErrors())
            return ValidationUtil.generateError(errors);
        TingkatanKelas tingkatanKelas = tingkatanKelasReq.convertToEntity();
        GlobalResponse<TingkatanKelas> response = tingkatanKelasService.update(uuid, tingkatanKelas);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Boolean>> delete(@PathVariable("uuid") String uuid){
        GlobalResponse<Boolean> response = tingkatanKelasService.delete(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
