package com.example.repository;

import com.example.domain.SequenceTestDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SequenceTestDomainRepository extends JpaRepository<SequenceTestDomain, Long> {
    @Query(value = "SELECT TEST_SEQ.nextval from dual", nativeQuery = true)
    int getSequence();
}
