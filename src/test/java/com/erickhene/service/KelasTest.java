package com.erickhene.service;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.KelasReq;
import com.erickhene.entity.impl.Kelas;
import com.erickhene.service.impl.KelasService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class KelasTest {
    @Autowired
    KelasService kelasService;
    @Test
    void getAllKelas(){
        log.info("getAllKelas");
        GlobalResponse<List<Kelas>> response = kelasService.getAll();
        log.info("Response = {}", response);
    }

    @Test
    void getByUUIDKelas(){
        log.info("getByUUIDKelas");
        GlobalResponse<Kelas> byUuid = kelasService.getByUuid("a94cfb0f-ae55-4ad3-9b8a-bd3688ea38a9");
        log.info("Response = {}", byUuid);
    }

    @Test
    void createKelas(){
        KelasReq req = new KelasReq();
        req.setName("X TKJ 3");
        req.setTingkatanKelasUuid("9df4c48a-7e2d-43d8-8a03-a2db8bb401b8");
        Kelas kelas = req.convertToEntity();
        GlobalResponse<Kelas> response = kelasService.create(kelas);
        log.info("Response = {}", response);
    }
}
