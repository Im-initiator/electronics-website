package com.electronics_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.electronics_store.model.entity.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByAccessToken(String token);

    Optional<TokenEntity> findByRefreshToken(String token);

    @Modifying
    @Query(
            "delete from TokenEntity t2 where t2.id IN (select tid from (select t.id AS tid from TokenEntity t where t.account.id = :userId order by t.createDate limit 1))")
    void deleteOldTokenByUser(Long userId);

    Optional<TokenEntity> findByIdAndAccount_Id(Long id, Long accountId);

    Optional<Integer> countAllByAccount_Id(Long userId);

    List<TokenEntity> findAllByAccount_Id(Long userId);
}
