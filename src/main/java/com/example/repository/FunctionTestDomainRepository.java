package com.example.repository;

import com.example.domain.FunctionTestDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FunctionTestDomainRepository extends JpaRepository<FunctionTestDomain, Long> {
    @Query(value = "SELECT FUNCTION_TEST(:str) from dual", nativeQuery = true)
    String functionTest(String str);
}
