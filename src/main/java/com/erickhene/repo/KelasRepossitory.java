package com.erickhene.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickhene.entity.impl.Kelas;

@Repository
public interface KelasRepossitory extends JpaRepository<Kelas, String> {
    
}
