package org.example.jpa.repository;

import org.example.jpa.domain.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select DISTINCT a from Category a join fetch a.items")
    public List<Category> findAllJoinFetch();

    @EntityGraph(attributePaths = "items")
    @Query("select DISTINCT a from Category a")
    public List<Category> findAllEntityGraph();
}
