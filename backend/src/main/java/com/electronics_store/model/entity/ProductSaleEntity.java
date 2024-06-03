package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu  trữ thông tin các sản phẩm được giảm giá
 */
@Entity
@Table(name = "product_sale")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductSaleEntity extends BaseEntity {
    @Column
    private Double discountAmount;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity products;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleEntity sale;
}
