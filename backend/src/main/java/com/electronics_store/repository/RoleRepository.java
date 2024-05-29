package com.electronics_store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronics_store.model.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByCode(String name);
}
