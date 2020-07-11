package org.example.batch.repository;

import org.example.batch.domain.RepeatStepExampleDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepeatStepExampleRepository extends JpaRepository<RepeatStepExampleDomain, String> {
}
