package com.erickhene.repo;

import com.erickhene.entity.impl.TahunAkademik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TahunAkademikRepository extends JpaRepository<TahunAkademik, String> {
    Optional<TahunAkademik> findByUuidAndEnabledTrue(String uuid);

    Optional<TahunAkademik> findByUuidAndEnabledTrueAndStatusTrue(String uuid);

    List<TahunAkademik> findAllByEnabledTrue();
}
