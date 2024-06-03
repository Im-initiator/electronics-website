package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * lưu trữ các trang mạng xã hội như tiktok youtube
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "social")
@Entity
public class SocialEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String link;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;
}
