package com.electronics_store.repository;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.ProductTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long>{

    Optional<ProductTypeEntity> findByIdAndState(Long id, State state);

    @Query("SELECT CASE WHEN COUNT(p)>0 THEN TRUE ELSE FALSE END FROM ProductTypeEntity p WHERE p.name = :name")
    boolean isNameExist(String name);

    @Query(value = "SELECT p FROM ProductTypeEntity p WHERE p.state = :state ORDER BY p.createDate",
    countQuery = "SELECT p FROM ProductTypeEntity p WHERE p.state = :state ORDER BY p.createDate"
    )
    Page<ProductTypeEntity> findAllByState(State state,Pageable pageable);

    @Query(value = "SELECT p FROM ProductTypeEntity p WHERE p.state = :state AND LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%')) ORDER BY p.createDate",
    countQuery = "SELECT p FROM ProductTypeEntity p WHERE p.state = :state AND LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%'))"
    )
    Page<ProductTypeEntity> findAllByStateAndNameContaining(State state, String name, Pageable pageable);

    @Query("SELECT p FROM ProductTypeEntity p WHERE p.state = :state")
    List<ProductTypeEntity>  getListByState(State state);

    @Query("SELECT p FROM ProductTypeEntity p JOIN FETCH p.category WHERE p.id = :id AND p.state = :state")
    Optional<ProductTypeEntity> getOneByIdAndStateIncludeCategory(Long id,State state);


}
