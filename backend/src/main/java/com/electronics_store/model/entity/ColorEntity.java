package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Lưu trữ màu của sản phẩm
 */
@Entity
@Table(name = "color")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColorEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column
    private Double price;

    @Column(nullable = false)
    private Integer quality;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
