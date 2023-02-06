package com.erickhene.service.impl;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.Siswa;
import com.erickhene.repo.SiswaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiswaService {
    private final SiswaRepository siswaRepository;

    public SiswaService(SiswaRepository siswaRepository) {
        this.siswaRepository = siswaRepository;
    }

    public GlobalResponse<List<Siswa>> getAll(){
        List<Siswa> all = siswaRepository.findAll();
        return new GlobalResponse<>(null, HttpStatus.OK.value(), all);
    }
}
