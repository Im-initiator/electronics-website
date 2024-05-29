package com.electronics_store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.electronics_store.model.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<AccountEntity> findByUserName(String userName);
}
