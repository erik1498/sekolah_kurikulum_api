package com.erickhene.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.erickhene.dao.MataPelajaranTabMapper;
import com.erickhene.dto.request.DataTableReq;
import com.erickhene.entity.impl.TahunAkademik;
import com.erickhene.repo.TahunAkademikRepository;
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
    private final MataPelajaranTabMapper tabMapper;
    private final TahunAkademikRepository tahunAkademikRepository;

    @Autowired
    public MataPelajaranService(MataPelajaranRepository repository, MataPelajaranTabMapper tabMapper, TahunAkademikRepository tahunAkademikRepository) {
        this.repository = repository;
        this.tabMapper = tabMapper;
        this.tahunAkademikRepository = tahunAkademikRepository;
    }

    @Override
    public GlobalResponse<List<MataPelajaran>> getAll(DataTableReq dataTableReq) {
        log.info("Begin [{}]", "getAllMataPelajaran");
        List<MataPelajaran> findAll = tabMapper.selectAll(DataTableReq.generateHashMap(dataTableReq));
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
            Optional<TahunAkademik> tahunAkademik = tahunAkademikRepository.findByUuidAndEnabledTrueAndStatusTrue(data.getTahunAkademikUuid());
            log.info("Tahun Akademik Present = {}", tahunAkademik.isPresent());
            if (tahunAkademik.isPresent()){
                return new GlobalResponse<>(null, HttpStatus.CREATED.value(), repository.save(data));
            }
            return new GlobalResponse<>("Tahun Akademik Doesn't Exist", HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            return new GlobalResponse<>(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        }
    }

    @Override
    public GlobalResponse<MataPelajaran> getByUuid(String uuid) {
        log.info("Begin [{}]", "getByUuidMataPelajaran");
        log.info("Uuid = {}", uuid);
        Optional<MataPelajaran> findById = tabMapper.selectByUuid(uuid);
        log.info("Mata Pelajaran Present = {}", findById.isPresent());
        if (findById.isPresent()) {
            log.info("Mata Pelajaran = {}", findById.get().toString());
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }

    @Override
    public GlobalResponse<MataPelajaran> update(String uuid, MataPelajaran data) {
        log.info("Begin [{}]", "UpdateMataPelajaran");
        log.info("Uuid = {}", uuid);
        try{
            Optional<MataPelajaran> findById = tabMapper.selectByUuid(uuid);
            log.info("Mata Pelajaran Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Mata Pelajaran = {}", findById.get().toString());
                MataPelajaran getByUuid = findById.get();
                getByUuid.setName(data.getName());
                getByUuid.setKkm(data.getKkm());
                getByUuid.setTahunAkademikUuid(data.getTahunAkademikUuid());
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
        log.info("Begin [{}]", "DeleteMataPelajaran");
        log.info("Uuid = {}", uuid);
        try{
            Optional<MataPelajaran> findById = tabMapper.selectByUuid(uuid);
            log.info("Mata Pelajaran Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Mata Pelajaran = {}", findById.get().toString());
                MataPelajaran getByUuid = findById.get();
                getByUuid.setEnabled(false);
                repository.save(getByUuid);
                log.info("Mata Pelajaran Enabled = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.NO_CONTENT.value(), true);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
