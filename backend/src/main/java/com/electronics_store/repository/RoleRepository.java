package com.electronics_store.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronics_store.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);

    Set<RoleEntity> findByIdIn(Set<Long> ids);

    @Query("SELECT r FROM RoleEntity r")
    Set<RoleEntity> getAll();

    @Query("SELECT CASE WHEN COUNT (r)>0 THEN TRUE ELSE FALSE END FROM RoleEntity r WHERE r.id IN :ids AND r.name IN :names")
    boolean isIdContainingName(Set<Long> ids, List<String> names);

}
