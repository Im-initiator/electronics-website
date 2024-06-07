package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ chi nhánh của một cửa hàng tại một nơi nào đó.
 */
@Table(name = "branch")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column(length = 20)
    private String phone;

    private String googleMap;

    @Column(nullable = false, columnDefinition = "NVARCHAR(50)")
    private String province;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private Set<EmployeeEntity> employees = new HashSet<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChatEntity> chats = new HashSet<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.PERSIST)
    Set<ProductEntity> products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;
}
