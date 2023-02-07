package com.erickhene.dao.service;

import com.erickhene.dao.mapper.MataPelajaranTabMapper;
import com.erickhene.entity.impl.MataPelajaran;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Configuration
public class MataPelajaranDao {
    private final MataPelajaranTabMapper mapper;

    public MataPelajaranDao(MataPelajaranTabMapper mapper) {
        this.mapper = mapper;
    }

    public List<MataPelajaran> selectAll(Map<String, Object> parameterMap){
        return mapper.selectAll(parameterMap);
    }

    public Optional<MataPelajaran> selectByUuid(String uuid){
        return mapper.selectByUuid(uuid);
    }
}
