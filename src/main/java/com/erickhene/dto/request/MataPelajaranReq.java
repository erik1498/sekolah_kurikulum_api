package com.erickhene.dto.request;

import javax.validation.constraints.NotNull;

import com.erickhene.entity.impl.MataPelajaran;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class MataPelajaranReq {
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "kkm is required")
    private Integer kkm;

    @NotNull(message = "tahun_akademik_uuid is required")
    @JsonAlias(value = "tahun_akademik_uuid")
    private String tahunAkademikUuid;

    public MataPelajaran convertToEntity() {
        MataPelajaran mataPelajaran = new MataPelajaran();
        mataPelajaran.setName(getName());
        mataPelajaran.setKkm(getKkm());
        mataPelajaran.setTahunAkademikUuid(getTahunAkademikUuid());
        return mataPelajaran;
    }
}
