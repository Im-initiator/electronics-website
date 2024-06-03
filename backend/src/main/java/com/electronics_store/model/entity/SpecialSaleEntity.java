package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ các loại  giảm giá đặc biệt  như giảm cho sinh viên, tặng kèm phụ kiện...
 */
@Entity
@Table(name = "special_sale")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialSaleEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(nullable = false)
    private Double discountAmount;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
