package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
    @Column(columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column
    private String logo;

    @OneToMany
    private Set<ProductEntity> products = new HashSet<>();
}
