package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ loại của product, ví dụ điện thoại thì có iphone, samsung...
 */
@Entity
@Table(name = "product_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(100)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.PERSIST)
    private Set<ProductEntity> products = new HashSet<>();
}
