package com.erickhene.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickhene.entity.impl.MataPelajaran;

import java.util.List;
import java.util.Optional;

@Repository
public interface MataPelajaranRepository extends JpaRepository<MataPelajaran, String> {

    Optional<MataPelajaran> findByUuidAndEnabledTrue(String uuid);

    List<MataPelajaran> findAllByEnabledTrue();
}
