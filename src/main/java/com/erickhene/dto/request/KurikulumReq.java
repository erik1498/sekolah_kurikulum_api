package com.erickhene.dto.request;

import com.erickhene.entity.impl.Kurikulum;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KurikulumReq {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "tahun_akademik_uuid is required")
    @JsonAlias("tahun_akademik_uuid")
    private String tahunAkademikUuid;

    public Kurikulum convertToEntity(){
        Kurikulum kurikulum = new Kurikulum();
        kurikulum.setName(getName());
        kurikulum.setTahunAkademikUuid(getTahunAkademikUuid());
        return kurikulum;
    }
}
