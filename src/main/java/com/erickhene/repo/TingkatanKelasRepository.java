package com.erickhene.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickhene.entity.impl.TingkatanKelas;

@Repository
public interface TingkatanKelasRepository extends JpaRepository<TingkatanKelas, String> {
    
}
