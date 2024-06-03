package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu  trữ bài viết của cửa hàng.
 */
@Entity
@Table(name = "new")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEntity extends BaseEntity {
    @Column(columnDefinition = "NVARCHAR(50)")
    private String category;

    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(nullable = false, columnDefinition = "NVARCHAR(400)")
    private String shortDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "VARCHAR(255)")
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;
}
