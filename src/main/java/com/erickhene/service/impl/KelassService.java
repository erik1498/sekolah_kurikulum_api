package com.erickhene.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erickhene.config.AppsConstant;
import com.erickhene.dao.KelasTabMapper;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.Kelas;
import com.erickhene.repo.KelasRepossitory;
import com.erickhene.service.BaseService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KelassService implements BaseService<Kelas> {

    final KelasRepossitory repository;
    final KelasTabMapper tabMapper;

    @Autowired
    public KelassService(KelasRepossitory repository, KelasTabMapper tabMapper){
        this.repository = repository;
        this.tabMapper = tabMapper;
    }

    @Override
    public GlobalResponse<List<Kelas>> getAll() {
        List<Kelas> findAll = this.tabMapper.selectAll();
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppsConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
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
        return new GlobalResponse<>(AppsConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }
    
}
