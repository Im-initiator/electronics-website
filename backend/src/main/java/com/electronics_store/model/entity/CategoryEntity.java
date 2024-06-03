package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ thể loại sản phẩm như điện thoại, laptop, phụ kiện....
 */
@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductTypeEntity> productTypes = new HashSet<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private Set<TechnicalSpecificationEntity> technicalSpecifications = new HashSet<>();
}
