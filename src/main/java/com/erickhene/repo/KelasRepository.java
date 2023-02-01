package com.erickhene.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickhene.entity.impl.Kelas;

import java.util.Optional;

@Repository
public interface KelasRepository extends JpaRepository<Kelas, String> {

}
