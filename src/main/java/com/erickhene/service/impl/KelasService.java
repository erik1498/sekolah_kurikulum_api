package com.erickhene.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erickhene.config.AppConstant;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.Kelas;
import com.erickhene.repo.KelasRepossitory;
import com.erickhene.service.BaseService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KelasService implements BaseService<Kelas> {

    final KelasRepossitory repository;

    @Autowired
    public KelasService(KelasRepossitory repository){
        this.repository = repository;
    }

    @Override
    public GlobalResponse<List<Kelas>> getAll() {
        List<Kelas> findAll = this.repository.findAll();
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<Kelas> create(Kelas kelas){
        try {
            return new GlobalResponse<>(null, HttpStatus.OK.value(), repository.save(kelas));
        }catch(Exception e){
            log.error("Error [{}]", e.getCause().getCause().getLocalizedMessage());
            return new GlobalResponse<>(e.getCause().getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<Kelas> getByUuid(String id) {
        Optional<Kelas> findById = repository.findById(id);
        if (findById.isPresent()) {
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }
    
}
