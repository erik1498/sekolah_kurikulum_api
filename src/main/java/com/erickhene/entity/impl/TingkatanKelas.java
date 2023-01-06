package com.erickhene.entity.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.erickhene.entity.Base;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tingkatan_kelas_tab")
@Setter
@Getter
public class TingkatanKelas extends Base {
    @Column
    private String nama;

    @JsonIgnore
    @OneToMany(
        mappedBy = "tingkatanKelas",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Kelas> kelas;
}
