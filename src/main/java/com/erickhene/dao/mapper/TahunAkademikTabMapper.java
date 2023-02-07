package com.erickhene.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TahunAkademikTabMapper {
    Boolean setTahunAkademikStatus(String uuid);
}
