package com.erickhene.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erickhene.config.AppConstant;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.MataPelajaran;
import com.erickhene.repo.MataPelajaranRepository;
import com.erickhene.service.BaseService;

@Service
public class MataPelajaranService implements BaseService<MataPelajaran> {

    private final MataPelajaranRepository repository;

    @Autowired
    public MataPelajaranService(MataPelajaranRepository repository) {
        this.repository = repository;
    }

    @Override
    public GlobalResponse<List<MataPelajaran>> getAll() {
        List<MataPelajaran> findAll = repository.findAll();
        if (!findAll.isEmpty()) {
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
        }
        return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
    }

    @Override
    public GlobalResponse<MataPelajaran> create(MataPelajaran data) {
        try {
            return new GlobalResponse<>(null, HttpStatus.OK.value(), repository.save(data));
        } catch (Exception e) {
            return new GlobalResponse<>(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        }
    }

    @Override
    public GlobalResponse<MataPelajaran> getByUuid(String id) {
        Optional<MataPelajaran> findById = repository.findById(id);
        if (findById.isPresent()) {
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }
    
}
