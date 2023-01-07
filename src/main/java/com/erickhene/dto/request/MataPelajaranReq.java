package com.erickhene.dto.request;

import javax.validation.constraints.NotNull;

import com.erickhene.entity.impl.MataPelajaran;

import lombok.Data;

@Data
public class MataPelajaranReq {
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "kkm is required")
    private Integer kkm;

    public MataPelajaran convertToEntity() {
        MataPelajaran mataPelajaran = new MataPelajaran();
        mataPelajaran.setName(name);
        mataPelajaran.setKkm(kkm);
        return mataPelajaran;
    }
}
