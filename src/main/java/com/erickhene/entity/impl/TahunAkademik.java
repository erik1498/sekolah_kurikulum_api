package com.erickhene.entity.impl;

import com.erickhene.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tahun_akademik_tab")
@Setter
@Getter
public class TahunAkademik extends Base {
    @Column(name = "ta_name", nullable = false)
    private String name;
    @Column(name = "ta_semester", nullable = false)
    private Integer semester;
    @Column(name = "ta_status", nullable = false)
    private Boolean status;
}
