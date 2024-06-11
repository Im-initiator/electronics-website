package com.electronics_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByState(State state);

    @Query("SELECT s FROM ServiceEntity s WHERE s.id = :id AND s.state = :state")
    Optional<ServiceEntity> findByIdAndState(Long id, State state);

    List<ServiceEntity> findAllByState(State state);

    List<ServiceEntity> findByStateAndNameContaining(State state, String name);

    Page<ServiceEntity> findAllByStateOrderByCreateDateDesc(State state, Pageable pageable);

    @Query(
            "SELECT s FROM ServiceEntity s WHERE LOWER(s.name) LIKE LOWER(concat('%',:name,'%')) AND s.state = :state ORDER BY s.createDate DESC")
    Page<ServiceEntity> findAllByNameAndStateOrderByCreateDateDESC(State state, String name, Pageable pageable);
}
