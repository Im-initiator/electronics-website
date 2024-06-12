package com.electronics_store.repository;

import com.electronics_store.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.electronics_store.model.entity.PromotionEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {

    List<PromotionEntity> findAllByState(State state);

    @Query(
            value =
                    "SELECT p FROM PromotionEntity p WHERE p.state = :state AND LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%')) ORDER BY p.createDate DESC",
            countQuery =
                    "SELECT COUNT(p) FROM PromotionEntity p WHERE p.state = :state AND LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<PromotionEntity> findAllByStateAndNameContaining(State state, String name, Pageable pageable);

    Page<PromotionEntity> findAllByState(State state, Pageable pageable);

    Optional<PromotionEntity> findByIdAndState(Long id, State state);
    
    
    
}
