package com.electronics_store.repository;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.CategoryEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

    Optional<CategoryEntity> findByIdAndState(Long id, State state);

    @Query("SELECT CASE WHEN COUNT(c)>0 THEN TRUE ELSE FALSE END FROM CategoryEntity c WHERE c.name = :name")
    boolean isNameExists(String name);

    @Query("SELECT c FROM CategoryEntity c WHERE c.state = :state ORDER BY c.createDate DESC ")
    Page<CategoryEntity> findAllByState(State state, Pageable pageable);

    @Query(value = "SELECT c FROM CategoryEntity c WHERE c.state = :state AND LOWER(c.name) LIKE LOWER(CONCAT('%',:name,'%')) ORDER BY c.createDate",
    countQuery = "SELECT c FROM CategoryEntity c WHERE c.state = :state AND LOWER(c.name) LIKE LOWER(CONCAT('%',:name,'%'))"
    )
    Page<CategoryEntity> findAllByStateAndNameContaining(State state, String name, Pageable pageable);



}
