package com.electronics_store.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ quảng cáo của tất  cả cửa hàng, quản cáo này có thể hiển thị khi người dùng vào trang, hoặc ở một trang riêng
 */
@Entity
@Table(name = "advertise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertiseEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column
    private String image;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate timeStart;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate timeEnd;

    @Column
    private String link;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;
}
