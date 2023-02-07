package com.erickhene.dao.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.erickhene.entity.impl.Kelas;

@Mapper
public interface KelasTabMapper {
    List<Kelas> selectAll(Map<String, Object> parameterMap);
    Optional<Kelas> selectByUuid(String uuid);
}
