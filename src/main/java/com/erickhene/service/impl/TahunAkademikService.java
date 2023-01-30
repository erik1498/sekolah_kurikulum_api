package com.erickhene.service.impl;

import com.erickhene.config.AppConstant;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.TahunAkademik;
import com.erickhene.repo.TahunAkademikRepository;
import com.erickhene.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        log.info("Begin [{}]", "getAllTahunAkademik");
        List<TahunAkademik> findAll = repository.findAllByEnabledTrue();
        log.info("Tahun Akademik Length = {}", findAll.size());
        if (findAll.isEmpty()){
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<TahunAkademik> create(TahunAkademik data) {
        log.info("Begin [{}]", "createTahunAkademik");
        log.info("Tahun Akademik = {}", data);
        try{
            return new GlobalResponse<>(null, HttpStatus.OK.value(), repository.save(data));
        }catch (Exception e){
            log.error("Error [{}]", e.getCause().getCause().getMessage());
            return new GlobalResponse<>(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<TahunAkademik> getByUuid(String uuid) {
        log.info("Begin [{}]", "getByUuidTahunAkademik");
        log.info("Uuid = {}", uuid);
        Optional<TahunAkademik> findById = repository.findByUuidAndEnabledTrue(uuid);
        log.info("Tahun Akademik Present = {}", findById.isPresent());
        if (findById.isPresent()) {
            log.info("Tahun Akademik = {}", findById.get().toString());
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value(), null);
    }

    @Override
    public GlobalResponse<TahunAkademik> update(String uuid, TahunAkademik data) {
        log.info("Begin [{}]", "UpdateTahunAkademik");
        log.info("Uuid = {}", uuid);
        try{
            Optional<TahunAkademik> findById = repository.findByUuidAndEnabledTrue(uuid);
            log.info("Tahun Akademik Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Tahun Akademik = {}", findById.get().toString());
                TahunAkademik getByUuid = findById.get();
                getByUuid.setName(data.getName());
                getByUuid.setSemester(data.getSemester());
                getByUuid.setUpdatedDate(new Date());
                repository.save(getByUuid);
                log.info("Updated to = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.OK.value(), getByUuid);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
