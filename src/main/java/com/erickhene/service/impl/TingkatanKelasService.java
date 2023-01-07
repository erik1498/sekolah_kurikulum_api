package com.erickhene.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erickhene.config.AppsConstant;
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
        List<TingkatanKelas> findAll = repository.findAll();
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppsConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>("", HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<TingkatanKelas> create(TingkatanKelas tingkatanKelas) {
        try {
            return new GlobalResponse<>("", HttpStatus.CREATED.value(), repository.save(tingkatanKelas));
        } catch (Exception e) {
            log.error("Error [{}]", e.getCause().getCause().getLocalizedMessage());
            return new GlobalResponse<>(e.getCause().getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<TingkatanKelas> getByUuid(String id) {
        Optional<TingkatanKelas> findById = repository.findById(id);
        if (findById.isPresent()) {
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppsConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
    }
    
}
