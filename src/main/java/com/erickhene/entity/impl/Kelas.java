package com.erickhene.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.erickhene.entity.Base;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kelas_tab")
@Setter
@Getter
public class Kelas extends Base {
    @Column(name = "k_name")
    private String name;

    @Column(name = "k_tingkatan_kelas_uuid")
    private String tingkatanUuid;

    @Column(name = "k_tahun_akademik_uuid")
    private String tahunAkademikUuid;
}
