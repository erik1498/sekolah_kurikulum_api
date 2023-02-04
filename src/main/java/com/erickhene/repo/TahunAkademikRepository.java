package com.erickhene.repo;

import com.erickhene.entity.impl.TahunAkademik;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TahunAkademikRepository extends JpaRepository<TahunAkademik, String> {
    Optional<TahunAkademik> findByUuidAndEnabledTrue(String uuid);

    Optional<TahunAkademik> findByUuidAndEnabledTrueAndStatusTrue(String uuid);

    Page<TahunAkademik> findAllByEnabledTrueAndNameContains(String name, Pageable pageable);
}
