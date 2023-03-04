package com.erickhene.service.impl;

import com.erickhene.config.AppConstant;
import com.erickhene.dao.service.KurikulumDao;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.DataTableReq;
import com.erickhene.entity.impl.Kurikulum;
import com.erickhene.entity.impl.TahunAkademik;
import com.erickhene.repo.KurikulumRepository;
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
public class KurikulumService implements BaseService<Kurikulum> {
    private final KurikulumRepository repository;
    private final TahunAkademikRepository tahunAkademikRepository;
    private final KurikulumDao kurikulumDao;

    public KurikulumService(KurikulumRepository repository, TahunAkademikRepository tahunAkademikRepository, KurikulumDao kurikulumDao) {
        this.repository = repository;
        this.tahunAkademikRepository = tahunAkademikRepository;
        this.kurikulumDao = kurikulumDao;
    }

    @Override
    public GlobalResponse<List<Kurikulum>> getAll(DataTableReq dataTableReq) {
        log.info("Begin [{}]", "getAllKurikulum");
        List<Kurikulum> findAll = kurikulumDao.selectAll(DataTableReq.generateHashMap(dataTableReq));
        log.info("Kurikulum Length = {}", findAll.size());
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<Kurikulum> create(Kurikulum kurikulum) {
        log.info("Begin [{}]", "createKurikulum");
        log.info("Kurikulum = {}", kurikulum);
        try{

            Optional<TahunAkademik> tahunAkademik = tahunAkademikRepository.findByUuidAndEnabledTrueAndStatusTrue(kurikulum.getTahunAkademikUuid());
            log.info("Tahun Akademik Present = {}", tahunAkademik.isPresent());
            if (tahunAkademik.isPresent()){
                return new GlobalResponse<>(null, HttpStatus.CREATED.value(), repository.save(kurikulum));
            }
            return new GlobalResponse<>("Tahun Akademik Doesn't Exist", HttpStatus.BAD_REQUEST.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getCause().getCause().getLocalizedMessage());
            return new GlobalResponse<>(e.getCause().getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<Kurikulum> getByUuid(String uuid) {
        log.info("Begin [{}]", "getByUuidKurikulum");
        log.info("Uuid = {}", uuid);
        Optional<Kurikulum> findById = kurikulumDao.selectByUuid(uuid);
        log.info("Kurikulum Present = {}", findById.isPresent());
        if (findById.isPresent()) {
            log.info("Kurikulum = {}", findById.get().toString());
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }

    @Override
    public GlobalResponse<Kurikulum> update(String uuid, Kurikulum data) {
        log.info("Begin [{}]", "UpdateKurikulum");
        log.info("Uuid = {}", uuid);
        try{
            Optional<Kurikulum> kurikulum = kurikulumDao.selectByUuid(uuid);
            log.info("Kurikulum Present = {}", kurikulum.isPresent());
            if (kurikulum.isPresent()){
                log.info("Kurikulum = {}", kurikulum.toString());
                Kurikulum getByUuid = kurikulum.get();
                getByUuid.setName(data.getName());
                getByUuid.setUpdatedDate(new Date());
                repository.save(getByUuid);
                log.info("Updated to = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.NO_CONTENT.value(), null);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public GlobalResponse<Boolean> delete(String uuid) {
        log.info("Begin [{}]", "DeleteKurikulum");
        log.info("Uuid = {}", uuid);
        try{
            Optional<Kurikulum> findById = kurikulumDao.selectByUuid(uuid);
            log.info("Kurikulum Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Kurikulum = {}", findById.get().toString());
                Kurikulum getByUuid = findById.get();
                getByUuid.setEnabled(false);
                repository.save(getByUuid);
                log.info("Kurikulum Enabled = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.OK.value(), true);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
