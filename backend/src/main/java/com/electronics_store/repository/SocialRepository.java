package com.electronics_store.repository;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.SocialEntity;

=======

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.SocialEntity;

>>>>>>> 0ab3c2ee411eb0e1ac6af7f9f6c004280bf7665d
public interface SocialRepository extends JpaRepository<SocialEntity, Long> {

    Optional<SocialEntity> findOneByIdAndState(Long id, State state);

    Optional<SocialEntity> findOneById(Long id);

    List<SocialEntity> findAllByState(State state);

    Page<SocialEntity> findAllByStateOrderByCreateDateDesc(State state, Pageable pageable);

    @Query(
            "SELECT s FROM SocialEntity s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',:name,'%')) AND s.state = :state ORDER BY s.createDate DESC")
    Page<SocialEntity> findAllByNameAndStateOrderByCreateDateDESC(State state, String name, Pageable pageable);
}
