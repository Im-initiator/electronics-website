package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ các nhóm product để hiên thị trên một trang chi tiết
 */
@Entity
@Table(name = "product_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntity extends BaseEntity {
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<ProductEntity> products = new HashSet<>();
}
