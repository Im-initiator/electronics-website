package com.electronics_store.repository;

import com.electronics_store.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<AccountEntity> findByUserName(String userName);

}
