package com.erickhene.service.impl;

import com.erickhene.dao.service.KurikulumDao;
import com.erickhene.dao.service.MataPelajaranDao;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.DataTableReq;
import com.erickhene.entity.impl.Kurikulum;
import com.erickhene.entity.impl.KurikulumRules;
import com.erickhene.entity.impl.MataPelajaran;
import com.erickhene.entity.impl.TingkatanKelas;
import com.erickhene.repo.KurikulumRulesRepository;
import com.erickhene.repo.TingkatanKelasRepository;
import com.erickhene.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class KurikulumRulesService implements BaseService<KurikulumRules> {
    private final KurikulumRulesRepository repository;
    private final MataPelajaranDao mataPelajaranDao;
    private final KurikulumDao kurikulumDao;
    private final TingkatanKelasRepository tingkatanKelasRepository;

    public KurikulumRulesService(KurikulumRulesRepository repository, MataPelajaranDao mataPelajaranDao, KurikulumDao kurikulumDao, TingkatanKelasRepository tingkatanKelasRepository) {
        this.repository = repository;
        this.mataPelajaranDao = mataPelajaranDao;
        this.kurikulumDao = kurikulumDao;
        this.tingkatanKelasRepository = tingkatanKelasRepository;
    }

    @Override
    public GlobalResponse<List<KurikulumRules>> getAll(DataTableReq dataTableReq) {
        return null;
    }

    @Override
    public GlobalResponse<KurikulumRules> create(KurikulumRules kurikulumRules) {
        log.info("Begin [{}]", "createKurikulumRules");
        log.info("KurikulumRules = {}", kurikulumRules);
        try {
            Optional<Kurikulum> kurikulum = kurikulumDao.selectByUuid(kurikulumRules.getKurikulumUuid());
            log.info("Kurikulum Present = {}", kurikulum.isPresent());
            if (!kurikulum.isPresent()) return new GlobalResponse<>("Kurikulum is not valid", HttpStatus.BAD_REQUEST.value());

            Optional<MataPelajaran> mataPelajaran = mataPelajaranDao.selectByUuid(kurikulumRules.getMataPelajaranUuid());
            log.info("Mata Pelajaran Present = {}", mataPelajaran.isPresent());
            if (!mataPelajaran.isPresent()) return new GlobalResponse<>("Mata Pelajaran is not valid", HttpStatus.BAD_REQUEST.value());

            Optional<TingkatanKelas> tingkatanKelas = tingkatanKelasRepository.findByUuidAndEnabledTrue(kurikulumRules.getTingkatanKelasUuid());
            log.info("Tingkatan Kelas Present = {}", tingkatanKelas.isPresent());
            if (!tingkatanKelas.isPresent()) return new GlobalResponse<>("Tingkatan Kelas is not valid", HttpStatus.BAD_REQUEST.value());

            return new GlobalResponse<>(null, HttpStatus.CREATED.value(), repository.save(kurikulumRules));
        }catch(Exception e){
            log.error("Error [{}]", e.getCause().getCause().getLocalizedMessage());
            return new GlobalResponse<>(e.getCause().getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<KurikulumRules> getByUuid(String id) {
        return null;
    }

    @Override
    public GlobalResponse<KurikulumRules> update(String id, KurikulumRules data) {
        return null;
    }

    @Override
    public GlobalResponse<Boolean> delete(String id) {
        return null;
    }
}
