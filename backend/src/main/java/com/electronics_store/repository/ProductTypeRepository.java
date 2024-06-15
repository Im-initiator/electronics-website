package com.electronics_store.repository;

import com.electronics_store.model.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long>{

}
