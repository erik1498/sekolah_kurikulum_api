package com.erickhene.dto.request;

import javax.validation.constraints.NotNull;

import com.erickhene.entity.impl.TingkatanKelas;

import lombok.Data;

@Data
public class TingkatanKelasReq {
    @NotNull(message = "name is required")
    private String name;

    public TingkatanKelas convertToEntity() {
        TingkatanKelas tingkatanKelas = new TingkatanKelas();
        tingkatanKelas.setName(getName());
        return tingkatanKelas;
    }
}
