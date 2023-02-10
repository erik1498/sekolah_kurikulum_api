package com.erickhene.dto.request;

import com.erickhene.entity.impl.KurikulumRules;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KurikulumRulesReq {
    @NotNull(message = "kurikulum_uuid is required")
    @JsonAlias("kurikulum_uuid")
    private String kurikulumUuid;
    @NotNull(message = "tingkatan_kelas_uuid is required")
    @JsonAlias("tingkatan_kelas_uuid")
    private String tingkatanKelasUuid;
    @NotNull(message = "mata_pelajaran_uuid is required")
    @JsonAlias("mata_pelajaran_uuid")
    private String mataPelajaranUuid;

    public KurikulumRules convertToEntity(){
        KurikulumRules kurikulumRules = new KurikulumRules();
        kurikulumRules.setKurikulumUuid(getKurikulumUuid());
        kurikulumRules.setTingkatanKelasUuid(getTingkatanKelasUuid());
        kurikulumRules.setMataPelajaranUuid(getMataPelajaranUuid());
        return kurikulumRules;
    }
}
