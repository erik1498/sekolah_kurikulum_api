package com.erickhene.repo;

import com.erickhene.entity.impl.TahunAkademik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TahunAkademikRepository extends JpaRepository<TahunAkademik, String> {
}
