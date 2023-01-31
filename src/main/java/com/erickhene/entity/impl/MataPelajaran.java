package com.erickhene.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.erickhene.entity.Base;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mata_pelajaran_tab")
public class MataPelajaran extends Base {
    @Column(name = "m_name", nullable = false)
    private String name;

    @Column(name = "m_kkm", nullable = false)
    private Integer kkm;
}
