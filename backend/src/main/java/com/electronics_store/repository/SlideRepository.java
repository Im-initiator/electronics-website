package com.electronics_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.SlideEntity;

public interface SlideRepository extends JpaRepository<SlideEntity, Long> {
    List<SlideEntity> findByState(State state);

    @Query("SELECT s FROM SlideEntity s WHERE s.id = :id AND s.state = :state")
    Optional<SlideEntity> findByIdAndState(Long id, State state);

    List<SlideEntity> findByStateAndNameContaining(State state, String name);

    Page<SlideEntity> findAllByStateOrderByCreateDateDesc(State state, Pageable pageable);
    @Query(
            "SELECT s FROM SlideEntity s WHERE LOWER(s.name) LIKE LOWER(concat('%',:name,'%')) AND s.state = :state ORDER BY s.createDate DESC")
    Page<SlideEntity> findAllByNameAndStateOrderByCreateDateDESC(State state, String name, Pageable pageable);
}
