package com.erickhene.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.erickhene.entity.impl.Kelas;

@Mapper
public interface KelasTabMapper {
    List<Kelas> selectAll();
}
