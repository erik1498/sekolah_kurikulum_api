package com.erickhene.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.erickhene.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
@Slf4j
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

    @Override
    public String toString(){
        ObjectMapper objectMapper = ObjectMapperUtil.generateObjectMapper();
        try {
            return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Error [{}]", e.getMessage());
            return e.getMessage();
        }
    }
}
