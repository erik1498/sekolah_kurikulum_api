package com.erickhene.dto.request;

import javax.validation.constraints.NotNull;

import com.erickhene.entity.impl.Kelas;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class KelasReq {
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "tingkatan_kelas_uuid is required")
    @JsonAlias(value = "tingkatan_kelas_uuid")
    private String tingkatanKelasUuid;

    public Kelas convertToEntity(){
        Kelas kelas = new Kelas();
        kelas.setName(getName());
        kelas.setTingkatanUuid(getTingkatanKelasUuid());
        return kelas;
    }
}
