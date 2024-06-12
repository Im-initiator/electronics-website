package com.electronics_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.electronics_store.enums.State;
import com.electronics_store.model.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByState(State state);

    @Query(
            value =
                    "SELECT p FROM PostEntity p WHERE p.state = :state AND LOWER(p.title) LIKE LOWER(CONCAT('%',:title,'%')) ORDER BY p.createDate DESC",
            countQuery =
                    "SELECT COUNT(p) FROM PostEntity p WHERE p.state = :state AND LOWER(p.title) LIKE LOWER(CONCAT('%',:title,'%'))")
    Page<PostEntity> findAllByStateAndTitleContaining(State state, String title, Pageable pageable);

    @Query(
            value =
                    "SELECT p FROM PostEntity p WHERE p.state = :state AND LOWER(p.category) LIKE LOWER(CONCAT('%',:category,'%')) ORDER BY p.createDate DESC",
            countQuery =
                    "SELECT COUNT(p) FROM PostEntity p WHERE p.state = :category AND LOWER(p.title) LIKE LOWER(CONCAT('%',:category,'%'))")
    Page<PostEntity> findAllByStateAndCategoryContaining(State state, String category, Pageable pageable);

    Page<PostEntity> findAllByState(State state, Pageable pageable);

    Optional<PostEntity> findByIdAndState(Long id, State state);
}
