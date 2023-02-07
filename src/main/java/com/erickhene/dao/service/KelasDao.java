package com.erickhene.dao.service;

import com.erickhene.dao.mapper.KelasTabMapper;
import com.erickhene.entity.impl.Kelas;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class KelasDao {
    private final KelasTabMapper mapper;

    public KelasDao(KelasTabMapper mapper) {
        this.mapper = mapper;
    }

    public List<Kelas> selectAll(Map<String, Object> parameterMap){
        return mapper.selectAll(parameterMap);
    }

    public Optional<Kelas> selectByUuid(String uuid){
        return mapper.selectByUuid(uuid);
    }
}
