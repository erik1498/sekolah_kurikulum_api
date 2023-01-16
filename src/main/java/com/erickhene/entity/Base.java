package com.erickhene.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public class Base implements Serializable{
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @CreationTimestamp
    @Column(name = "created_date", updatable = false, nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "enabled")
    private Boolean enabled = Boolean.TRUE;
}
