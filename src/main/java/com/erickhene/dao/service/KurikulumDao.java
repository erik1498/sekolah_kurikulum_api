package com.erickhene.dao.service;

import com.erickhene.dao.mapper.KurikulumTabMapper;
import com.erickhene.entity.impl.Kurikulum;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class KurikulumDao {

    private final KurikulumTabMapper mapper;

    public KurikulumDao(KurikulumTabMapper mapper) {
        this.mapper = mapper;
    }

    public List<Kurikulum> selectAll(Map<String, Object> parameterMap){
        return mapper.selectAll(parameterMap);
    }
    public Optional<Kurikulum> selectByUuid(String uuid){return mapper.selectByUuid(uuid); }
}
