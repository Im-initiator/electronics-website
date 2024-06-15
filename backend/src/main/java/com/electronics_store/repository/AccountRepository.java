package com.electronics_store.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.electronics_store.enums.State;
import com.electronics_store.enums.UserStatus;
import com.electronics_store.model.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @EntityGraph(attributePaths = {"roles","employee"})
    Optional<AccountEntity> findByUserName(String userName);

    @Query(
            value =
                    "SELECT a FROM AccountEntity a left join fetch a.roles left join fetch a.employee WHERE a.state = :state and a.status = :status order by a.createDate desc",
            countQuery = "SELECT COUNT(a) FROM AccountEntity a WHERE a.state = :state and a.status = :status")
    Page<AccountEntity> findAllAccountActiveOrderByCreatedDate(State state, UserStatus status, Pageable pageable);

    @Query(
            value =
                    "SELECT a FROM AccountEntity a left join fetch a.roles left join fetch a.employee WHERE a.state = :state and a.status = :status and LOWER(a.userName) LIKE LOWER(CONCAT('%',:name,'%')) order by a.createDate desc",
            countQuery =
                    "SELECT COUNT(a) FROM AccountEntity  a WHERE a.state = :state and a.status = :status and LOWER(a.userName) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<AccountEntity> findAllAccountActiveAndNameContainOrderByCreatedDate(
            State state, UserStatus status, String name, Pageable pageable);

    @Query("SELECT a FROM AccountEntity a left join fetch a.roles left join fetch a.employee WHERE a.id = :id")
    Optional<AccountEntity> findAccountById(Long id);

    @Query("SELECT CASE WHEN COUNT (a) > 0  THEN TRUE ELSE FALSE END FROM AccountEntity a WHERE a.userName = :username")
    boolean isExitingUserName(String username);

    @Query("SELECT CASE WHEN COUNT (a) > 0  THEN TRUE ELSE FALSE END FROM AccountEntity a WHERE a.email = :email")
    boolean isExitingEmail(String email);

}
