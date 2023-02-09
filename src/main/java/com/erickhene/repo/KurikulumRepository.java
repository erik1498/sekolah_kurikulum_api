package com.erickhene.repo;

import com.erickhene.entity.impl.Kurikulum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KurikulumRepository extends JpaRepository<Kurikulum, String> {
}
