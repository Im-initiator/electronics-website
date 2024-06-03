package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ đơn hàng của các  tài khoản và sản phẩm
 */
@Entity
@Table(name = "product_order")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity {
    @Column(columnDefinition = "NVARCHAR(255)")
    private String receiveAddress;

    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String paymentMethod;

    @Column(nullable = false)
    private Integer paymentStatus;

    @Column(nullable = false)
    private Double price;

    @Column
    private Float discount;

    @Column
    private String discountDescription;

    @Column
    private Float shipCost;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String receivePhone;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String shipPlace;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(nullable = false)
    private Double productPrice;

    @Column(nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
