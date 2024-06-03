package com.electronics_store.model.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ thông tin về giảm giá
 */
@Entity
@Table(name = "sale")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeStart;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeEnd;

    @Column
    private Integer priority;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductSaleEntity> productSales = new HashSet<>();
}
