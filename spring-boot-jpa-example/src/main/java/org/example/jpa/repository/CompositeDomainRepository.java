package org.example.jpa.repository;

import org.example.jpa.domain.CompositeDomain;
import org.example.jpa.domain.EmbeddedDomain;
import org.example.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositeDomainRepository extends JpaRepository<CompositeDomain, EmbeddedDomain> {
}
