package com.erickhene.service.impl;

import com.erickhene.config.AppConstant;
import com.erickhene.dao.TahunAkademikTabMapper;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.DataTableReq;
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
    private final TahunAkademikTabMapper tabMapper;

    public TahunAkademikService(TahunAkademikRepository repository, TahunAkademikTabMapper tabMapper) {
        this.repository = repository;
        this.tabMapper = tabMapper;
    }

    @Override
    public GlobalResponse<List<TahunAkademik>> getAll(DataTableReq dataTableReq) {
        log.info("Begin [{}]", "getAllTahunAkademik");
        dataTableReq = dataTableReq == null ? new DataTableReq() : dataTableReq;
        List<TahunAkademik> findAll = repository.findAllByEnabledTrueAndNameContains(dataTableReq.getSearch(), DataTableReq.generatePageableData(dataTableReq)).getContent();
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
            return new GlobalResponse<>(null, HttpStatus.CREATED.value(), repository.save(data));
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

    @Override
    public GlobalResponse<Boolean> delete(String uuid) {
        log.info("Begin [{}]", "DeleteTahunAkademik");
        log.info("Uuid = {}", uuid);
        try{
            Optional<TahunAkademik> findById = repository.findByUuidAndEnabledTrue(uuid);
            log.info("Tahun Akademik Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Tahun Akademik = {}", findById.get().toString());
                TahunAkademik getByUuid = findById.get();
                getByUuid.setEnabled(false);
                repository.save(getByUuid);
                log.info("Tahun Akademik Enabled = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.NO_CONTENT.value(), true);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public GlobalResponse<Boolean> activeTahunAkademik(String uuid){
        log.info("Begin [{}]", "activeTahunAkademik");
        log.info("UUID = {}", uuid);
        try{
            Optional<TahunAkademik> enabledTrue = repository.findByUuidAndEnabledTrue(uuid);
            log.info("Tahun Akademik Present = {}", enabledTrue.isPresent());
            if (enabledTrue.isPresent()){
                Boolean tahunAkademikStatus = tabMapper.setTahunAkademikStatus(uuid);
                if (tahunAkademikStatus == Boolean.TRUE){
                    log.info("Active Success");
                    return new GlobalResponse<>("Success", HttpStatus.NO_CONTENT.value());
                }
                log.info("Active Failed");
                return new GlobalResponse<>("Failed", HttpStatus.BAD_REQUEST.value());
            }
            return new GlobalResponse<>("Tahun Akademik Doesn't Exist", HttpStatus.BAD_REQUEST.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }
}
