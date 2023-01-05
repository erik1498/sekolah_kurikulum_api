package com.erickhene.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickhene.entity.Kelas;
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
    public List<Kelas> getAllKelas() {
        return this.repository.findAll();
    }
    
}
