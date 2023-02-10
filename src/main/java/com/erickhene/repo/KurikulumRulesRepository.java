package com.erickhene.repo;

import com.erickhene.entity.impl.KurikulumRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KurikulumRulesRepository extends JpaRepository<KurikulumRules, String> {
}
