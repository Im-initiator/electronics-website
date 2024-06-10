package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * lưu trữ slide show hiển thị đầu trang web
 */
@Entity
@Table(name = "slide")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column(nullable = false, columnDefinition = "NVARCHAR(100)")
    private String shortDescription;

    @Column(nullable = false)
    private String image;

    @Column(columnDefinition = "TEXT")
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;
}
