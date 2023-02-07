package com.erickhene.dao.service;

import com.erickhene.dao.mapper.TahunAkademikTabMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TahunAkademikDao {
    private final TahunAkademikTabMapper mapper;

    public TahunAkademikDao(TahunAkademikTabMapper mapper) {
        this.mapper = mapper;
    }
    public Boolean setTahunAkademikStatus(String uuid){
        return mapper.setTahunAkademikStatus(uuid);
    }
}
