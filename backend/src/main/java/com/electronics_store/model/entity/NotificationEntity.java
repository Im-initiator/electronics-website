package com.electronics_store.model.entity;

import jakarta.persistence.*;

import com.electronics_store.enums.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ thông tin thông báo từ cửa hàng đến các ta khoản
 */
@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "NVARCHAR(300)")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    @Column
    private String link;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;
}
