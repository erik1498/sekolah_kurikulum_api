package com.erickhene.service.impl;

import java.util.List;
import java.util.Optional;

import com.erickhene.entity.impl.TahunAkademik;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erickhene.config.AppConstant;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.TingkatanKelas;
import com.erickhene.repo.TingkatanKelasRepository;
import com.erickhene.service.BaseService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TingkatanKelasService implements BaseService<TingkatanKelas> {

    private final TingkatanKelasRepository repository;
    
    public TingkatanKelasService(TingkatanKelasRepository repository) {
        this.repository = repository;
    }

    @Override
    public GlobalResponse<List<TingkatanKelas>> getAll() {
        log.info("Begin [{}]", "getAllTahunAkademik");
        List<TingkatanKelas> findAll = repository.findAll();
        log.info("Tingkatan Kelas Length = {}", findAll.size());
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>("", HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<TingkatanKelas> create(TingkatanKelas tingkatanKelas) {
        log.info("Begin [{}]", "createTingkatanKelas");
        log.info("Tingkatan Kelas = {}", tingkatanKelas);
        try {
            return new GlobalResponse<>("", HttpStatus.CREATED.value(), repository.save(tingkatanKelas));
        } catch (Exception e) {
            log.error("Error [{}]", e.getCause().getCause().getLocalizedMessage());
            return new GlobalResponse<>(e.getCause().getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<TingkatanKelas> getByUuid(String uuid) {
        log.info("Begin [{}]", "getByUuidTingkatanKelas");
        log.info("Uuid = {}", uuid);
        Optional<TingkatanKelas> findById = repository.findById(uuid);
        log.info("Tingkatan Kelas Present = {}", findById.isPresent());
        if (findById.isPresent()) {
            log.info("Tingkatan Kelas = {}", findById.get().toString());
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
    }
    
}
