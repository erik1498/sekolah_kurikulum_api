package com.erickhene.service;

import java.util.List;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.entity.impl.Kelas;

public interface IKelasService {
    GlobalResponse<List<Kelas>> getAll();

    GlobalResponse<Kelas> create(Kelas kelas);
}
