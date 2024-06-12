package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ thông tin khuyến mại như khuyến mại cho sinh viên, lên đời....áp dụng cho toàn bộ cửa hàng
 * image lưu khi cần quảng cáo
 */
@Entity
@Table(name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String image;

    @Column(nullable = false)
    private Double percent;

    @Column(nullable = false)
    private Double maxDiscount;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;
}
