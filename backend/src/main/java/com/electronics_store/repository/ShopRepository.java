package com.electronics_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.electronics_store.model.entity.ShopEntity;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    @Query(value = "SELECT * FROM shop  LIMIT 1", nativeQuery = true)
    ShopEntity getShop();
}
