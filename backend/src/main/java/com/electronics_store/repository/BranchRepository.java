package com.electronics_store.repository;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<BranchEntity,Long> {

    @Query(value = "SELECT b FROM BranchEntity b WHERE b.state = :state ORDER BY b.createDate")
    Page<BranchEntity> getPageByState(State state, Pageable pageable);

    @Query(
    value = "SELECT b FROM BranchEntity b WHERE b.state = :state AND LOWER(b.name) LIKE lower(concat('%',:name,'%')) ORDER BY b.createDate",
    countQuery = "SELECT b FROM BranchEntity b WHERE b.state = :state AND LOWER(b.name) LIKE lower(concat('%',:name,'%'))"
    )
    Page<BranchEntity> getPageByStateAndNameContaining(State state, String name,Pageable pageable);

    @Query(value = "SELECT b FROM BranchEntity b WHERE b.state = :state AND LOWER(b.province) LIKE LOWER(CONCAT('%',:province,'%')) ORDER BY b.createDate",
    countQuery = "SELECT b FROM BranchEntity b WHERE b.state = :state AND LOWER(b.province) LIKE LOWER(CONCAT('%',:province,'%'))"
    )
    Page<BranchEntity> getPageByStateAndProvinceContaining(State state,String province,Pageable pageable);

    Optional<BranchEntity> findByProvince(String province);

    Optional<BranchEntity> findByIdAndState(Long id, State state);


}
