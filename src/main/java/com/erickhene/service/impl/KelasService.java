package com.erickhene.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.erickhene.entity.impl.TahunAkademik;
import com.erickhene.repo.TahunAkademikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erickhene.config.AppConstant;
import com.erickhene.dao.KelasTabMapper;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.Kelas;
import com.erickhene.repo.KelasRepository;
import com.erickhene.service.BaseService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KelasService implements BaseService<Kelas> {

    final KelasRepository repository;
    final KelasTabMapper tabMapper;
    final TahunAkademikRepository tahunAkademikRepository;

    @Autowired
    public KelasService(KelasRepository repository, KelasTabMapper tabMapper, TahunAkademikRepository tahunAkademikRepository){
        this.repository = repository;
        this.tabMapper = tabMapper;
        this.tahunAkademikRepository = tahunAkademikRepository;
    }

    @Override
    public GlobalResponse<List<Kelas>> getAll() {
        log.info("Begin [{}]", "getAllKelas");
        List<Kelas> findAll = this.tabMapper.selectAll();
        log.info("Kelas Length = {}", findAll.size());
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<Kelas> create(Kelas kelas){
        log.info("Begin [{}]", "CreateKelas");
        log.info("Kelas = {}", kelas);
        try {
            Optional<TahunAkademik> tahunAkademik = tahunAkademikRepository.findByUuidAndEnabledTrueAndStatusTrue(kelas.getTahunAkademikUuid());
            log.info("Tahun Akademik Present = {}", tahunAkademik.isPresent());
            if (tahunAkademik.isPresent()){
                return new GlobalResponse<>(null, HttpStatus.CREATED.value(), repository.save(kelas));
            }
            return new GlobalResponse<>("Tahun Akademik Doesn't Exist", HttpStatus.BAD_REQUEST.value());
        }catch(Exception e){
            log.error("Error [{}]", e.getCause().getCause().getLocalizedMessage());
            return new GlobalResponse<>(e.getCause().getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<Kelas> getByUuid(String uuid) {
        log.info("Begin [{}]", "getByUuidKelas");
        log.info("Uuid = {}", uuid);
        Optional<Kelas> findById = tabMapper.selectByUuid(uuid);
        log.info("Kelas Present = {}", findById.isPresent());
        if (findById.isPresent()) {
            log.info("Kelas = {}", findById.get().toString());
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }

    @Override
    public GlobalResponse<Kelas> update(String uuid, Kelas data) {
        log.info("Begin [{}]", "UpdateKelas");
        log.info("Uuid = {}", uuid);
        try{
            Optional<Kelas> findById = tabMapper.selectByUuid(uuid);
            log.info("Kelas Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Kelas = {}", findById.get().toString());
                Kelas getByUuid = findById.get();
                getByUuid.setName(data.getName());
                getByUuid.setUpdatedDate(new Date());
                getByUuid.setTingkatanUuid(data.getTingkatanUuid());
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
        log.info("Begin [{}]", "DeleteKelas");
        log.info("Uuid = {}", uuid);
        try{
            Optional<Kelas> findById = tabMapper.selectByUuid(uuid);
            log.info("Kelas Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Kelas = {}", findById.get().toString());
                Kelas getByUuid = findById.get();
                getByUuid.setEnabled(false);
                repository.save(getByUuid);
                log.info("Kelas Enabled = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.NO_CONTENT.value(), true);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
