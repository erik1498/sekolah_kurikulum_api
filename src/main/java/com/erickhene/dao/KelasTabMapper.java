package com.erickhene.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.erickhene.entity.impl.Kelas;

@Mapper
public interface KelasTabMapper {
    List<Kelas> selectAll();
    Optional<Kelas> selectByUuid(String uuid);
}
