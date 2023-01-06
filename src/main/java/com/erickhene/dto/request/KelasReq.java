package com.erickhene.dto.request;

import javax.validation.constraints.NotNull;

import com.erickhene.entity.impl.Kelas;

import lombok.Data;

@Data
public class KelasReq {
    @NotNull(message = "name is required")
    private String name;

    public Kelas convertToEntity(){
        Kelas kelas = new Kelas();
        kelas.setName(getName());
        return kelas;
    }
}
