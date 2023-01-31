package com.erickhene.entity.impl;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.erickhene.entity.Base;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tingkatan_kelas_tab")
@Setter
@Getter
public class TingkatanKelas extends Base {
    @Column(name = "tk_name")
    private String name;
}
