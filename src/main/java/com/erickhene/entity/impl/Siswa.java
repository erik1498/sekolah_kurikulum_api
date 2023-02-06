package com.erickhene.entity.impl;

import com.erickhene.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "siswa_tab")
@Setter
@Getter
public class Siswa extends Base {
    @Column(name = "s_name")
    private String name;
    @Column(name = "s_kelas_uuid")
    private String kelasUuid;
}
