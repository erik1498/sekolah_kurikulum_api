package com.erickhene.controller;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.TahunAkademikReq;
import com.erickhene.entity.impl.TahunAkademik;
import com.erickhene.service.impl.TahunAkademikService;
import com.erickhene.util.ValidationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tahun_akademik/")
public class TahunAkademikController {
    private final TahunAkademikService tahunAkademikService;

    public TahunAkademikController(TahunAkademikService tahunAkademikService) {
        this.tahunAkademikService = tahunAkademikService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<TahunAkademik>>> getAll(){
        GlobalResponse<List<TahunAkademik>> response = tahunAkademikService.getAll();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<GlobalResponse<TahunAkademik>> getByUuid(@PathVariable("uuid") String uuid){
        GlobalResponse<TahunAkademik> response = tahunAkademikService.getByUuid(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    @PostMapping
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody TahunAkademikReq tahunAkademikReq, Errors errors){
        if (errors.hasErrors())
            return ValidationUtil.generateError(errors);
        GlobalResponse<TahunAkademik> response = tahunAkademikService.create(tahunAkademikReq.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<GlobalResponse<?>> update(@Valid @RequestBody TahunAkademikReq tahunAkademikReq, @PathVariable("uuid") String uuid, Errors errors){
        if (errors.hasErrors())
            return ValidationUtil.generateError(errors);
        TahunAkademik tahunAkademik = tahunAkademikReq.convertToEntity();
        GlobalResponse<TahunAkademik> response = tahunAkademikService.update(uuid, tahunAkademik);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
