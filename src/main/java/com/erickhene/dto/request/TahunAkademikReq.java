package com.erickhene.dto.request;

import com.erickhene.entity.impl.TahunAkademik;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TahunAkademikReq {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "semester is required")
    private Integer semester;

    public TahunAkademik convertToEntity(){
        TahunAkademik tahunAkademik = new TahunAkademik();
        tahunAkademik.setName(getName());
        tahunAkademik.setSemester(getSemester());
        tahunAkademik.setStatus(Boolean.FALSE);
        return tahunAkademik;
    }
}
