package com.erickhene.controller;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.DataTableReq;
import com.erickhene.dto.request.KurikulumReq;
import com.erickhene.entity.impl.Kurikulum;
import com.erickhene.service.impl.KurikulumService;
import com.erickhene.util.ValidationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/kurikulum/")
public class KurikulumController {
    private final KurikulumService kurikulumService;

    public KurikulumController(KurikulumService kurikulumService) {
        this.kurikulumService = kurikulumService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Kurikulum>>> getAll(@RequestBody(required = false) DataTableReq dataTableReq){
        GlobalResponse<List<Kurikulum>> response = kurikulumService.getAll(dataTableReq);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Kurikulum>> getByUuid(@PathVariable("uuid") String uuid){
        GlobalResponse<Kurikulum> response = kurikulumService.getByUuid(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody KurikulumReq kurikulumReq, Errors errors){
        if (errors.hasErrors()){
            return ValidationUtil.generateError(errors);
        }
        GlobalResponse<Kurikulum> response = kurikulumService.create(kurikulumReq.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Kurikulum>> update(@RequestBody KurikulumReq kurikulumReq, @PathVariable("uuid") String uuid){
        GlobalResponse<Kurikulum> response = kurikulumService.update(uuid, kurikulumReq.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Boolean>> delete(@PathVariable("uuid") String uuid) {
        GlobalResponse<Boolean> response = kurikulumService.delete(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
