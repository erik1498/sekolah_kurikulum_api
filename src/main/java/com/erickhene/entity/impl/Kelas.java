package com.erickhene.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "tingkatan_kelas_uuid", nullable = false)
    private TingkatanKelas tingkatanKelas;
}
