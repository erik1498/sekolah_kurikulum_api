package com.erickhene.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TahunAkademikTabMapper {
    Boolean setTahunAkademikStatus(String uuid);
}
