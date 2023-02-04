package com.erickhene.dao;

import com.erickhene.entity.impl.MataPelajaran;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MataPelajaranTabMapper {
    List<MataPelajaran> selectAll(Map<String, Object> parameterMap);
    Optional<MataPelajaran> selectByUuid(String uuid);
}
