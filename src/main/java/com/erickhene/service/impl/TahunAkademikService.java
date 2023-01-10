package com.erickhene.service.impl;

import com.erickhene.config.AppConstant;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.TahunAkademik;
import com.erickhene.repo.TahunAkademikRepository;
import com.erickhene.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TahunAkademikService implements BaseService<TahunAkademik> {
    private final TahunAkademikRepository repository;

    public TahunAkademikService(TahunAkademikRepository repository) {
        this.repository = repository;
    }

    @Override
    public GlobalResponse<List<TahunAkademik>> getAll() {
        List<TahunAkademik> all = repository.findAll();
        if (all.isEmpty()){
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>(null, HttpStatus.OK.value(), all);
    }

    @Override
    public GlobalResponse<TahunAkademik> create(TahunAkademik data) {
        try{
            return new GlobalResponse<>(null, HttpStatus.OK.value(), repository.save(data));
        }catch (Exception e){
            log.error("Error [{}]", e.getCause().getCause().getMessage());
            return new GlobalResponse<>(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<TahunAkademik> getByUuid(String id) {
        Optional<TahunAkademik> byId = repository.findById(id);
        if (byId.isPresent())
            return new GlobalResponse<>(null, HttpStatus.OK.value(), byId.get());
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value(), null);
    }
}
