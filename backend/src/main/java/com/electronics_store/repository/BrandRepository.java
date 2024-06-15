package com.electronics_store.repository;

import com.electronics_store.enums.State;
import com.electronics_store.mapper.BrandMapper;
import com.electronics_store.model.entity.BranchEntity;
import com.electronics_store.model.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long>{

    @Query(value = "SELECT b FROM BrandEntity b LEFT JOIN FETCH b.category WHERE b.id = :id AND b.state = :state")
    Optional<BrandEntity> findByIdAndState(Long id, State state);

    @Query(value = "SELECT b FROM BranchEntity b WHERE b.state = :state AND LOWER(b.name) LIKE LOWER(CONCAT('%',:name,'%')) ORDER BY b.id DESC",
        countQuery = "SELECT COUNT(b) FROM BranchEntity b WHERE b.state = :state AND LOWER(b.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<BrandEntity> getAllByStateAndName(State state, String name, Pageable pageable);

    Page<BrandEntity> findAllByState(State state, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END FROM BrandEntity b WHERE b.name = :name")
    boolean isExistsBrandName(String name);

}
