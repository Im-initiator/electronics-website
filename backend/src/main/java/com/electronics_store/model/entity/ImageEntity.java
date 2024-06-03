package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Lưu trữ ảnh của  sản phẩm
 */
@Table(name = "image")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageEntity extends BaseEntity {
    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
