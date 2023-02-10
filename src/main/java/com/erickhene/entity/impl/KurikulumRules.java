package com.erickhene.entity.impl;

import com.erickhene.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "kurikulum_rules_tab")
@Setter
@Getter
public class KurikulumRules extends Base {
    @Column(name = "kr_kurikulum_uuid", nullable = false)
    private String kurikulumUuid;
    @Column(name = "kr_tingkatan_kelas__uuid", nullable = false)
    private String tingkatanKelasUuid;
    @Column(name = "kr_mata_pelajaran__uuid", nullable = false)
    private String mataPelajaranUuid;
}
