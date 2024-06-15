package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ các hãng của sản phẩm
 */
@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity extends BaseEntity {
    @Column(columnDefinition = "NVARCHAR(100)",nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String logo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany
    private Set<ProductEntity> products = new HashSet<>();




}
