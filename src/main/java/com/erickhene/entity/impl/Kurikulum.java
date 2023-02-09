package com.erickhene.entity.impl;

import com.erickhene.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "kurikulum_tab")
@Setter
@Getter
public class Kurikulum extends Base {
    @Column(name = "kk_name", nullable = false)
    private String name;

    @Column(name = "kk_tahun_akademik_uuid")
    private String tahunAkademikUuid;
}
