package com.electronics_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.SocialEntity;

public interface SocialRepository extends JpaRepository<SocialEntity, Long> {

    Optional<SocialEntity> findOneByIdAndState(Long id, State state);

    Optional<SocialEntity> findOneById(Long id);

    List<SocialEntity> findAllByState(State state);

    Page<SocialEntity> findAllByStateOrderByCreateDateDesc(State state, Pageable pageable);

    @Query(
            "SELECT s FROM SocialEntity s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',:name,'%')) AND s.state = :state ORDER BY s.createDate DESC")
    Page<SocialEntity> findAllByNameAndStateOrderByCreateDateDESC(State state, String name, Pageable pageable);
}
