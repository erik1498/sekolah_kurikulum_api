package com.erickhene.dao.mapper;

import com.erickhene.entity.impl.Kurikulum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface KurikulumTabMapper {
    List<Kurikulum> selectAll(Map<String, Object> parameterMap);
    Optional<Kurikulum> selectByUuid(String uuid);
}
