package com.erickhene.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.erickhene.config.AppConstant;
import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.Kelas;
import com.erickhene.repo.KelasRepository;
import com.erickhene.service.IKelasService;

@Service
public class KelasService implements IKelasService {

    final KelasRepository repository;

    @Autowired
    public KelasService(KelasRepository repository){
        this.repository = repository;
    }

    @Override
    public GlobalResponse<List<Kelas>> getAll() {
        List<Kelas> findAll = this.repository.findAll();
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value(), null);
        }
        return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<Kelas> create(Kelas kelas) {
        Kelas save = repository.save(kelas);
        return new GlobalResponse<>(null, HttpStatus.OK.value(), save);
    }
    
}
