package com.erickhene.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickhene.entity.impl.MataPelajaran;

@Repository
public interface MataPelajaranRepository extends JpaRepository<MataPelajaran, String> {
    
}
