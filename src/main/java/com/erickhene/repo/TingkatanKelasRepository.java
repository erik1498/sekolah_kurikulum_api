package com.erickhene.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickhene.entity.impl.TingkatanKelas;

import java.util.Optional;

@Repository
public interface TingkatanKelasRepository extends JpaRepository<TingkatanKelas, String> {
    Optional<TingkatanKelas> findByUuidAndEnabledTrue(String uuid);
    Page<TingkatanKelas> findAllByEnabledTrueAndNameContains(String name, Pageable pageable);
}
