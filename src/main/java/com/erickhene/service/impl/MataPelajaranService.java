package com.erickhene.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erickhene.config.AppConstant;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.MataPelajaran;
import com.erickhene.repo.MataPelajaranRepository;
import com.erickhene.service.BaseService;

@Service
@Slf4j
public class MataPelajaranService implements BaseService<MataPelajaran> {

    private final MataPelajaranRepository repository;

    @Autowired
    public MataPelajaranService(MataPelajaranRepository repository) {
        this.repository = repository;
    }

    @Override
    public GlobalResponse<List<MataPelajaran>> getAll() {
        log.info("Begin [{}]", "getAllMataPelajaran");
        List<MataPelajaran> findAll = repository.findAll();
        log.info("Mata Pelajaran Length = {}", findAll.size());
        if (!findAll.isEmpty()) {
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
        }
        return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
    }

    @Override
    public GlobalResponse<MataPelajaran> create(MataPelajaran data) {
        log.info("Begin [{}]", "createMataPelajaran");
        log.info("Mata Pelajaran = {}", data);
        try {
            return new GlobalResponse<>(null, HttpStatus.OK.value(), repository.save(data));
        } catch (Exception e) {
            return new GlobalResponse<>(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        }
    }

    @Override
    public GlobalResponse<MataPelajaran> getByUuid(String uuid) {
        log.info("Begin [{}]", "getByUuidMataPelajaran");
        log.info("Uuid = {}", uuid);
        Optional<MataPelajaran> findById = repository.findById(uuid);
        log.info("Mata Pelajaran Present = {}", findById.isPresent());
        if (findById.isPresent()) {
            log.info("Mata Pelajaran = {}", findById.get().toString());
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }
    
}
