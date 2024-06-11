package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import com.electronics_store.enums.UserStatus;
import com.electronics_store.mapper.UserStatusConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ thông tin tài koản
 */
@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity extends BaseEntity {
    // unique không phân biệt chữ hoa chữ thường
    @Column(unique = true, nullable = false, length = 50, columnDefinition = "VARCHAR(50) COLLATE utf8mb4_unicode_ci")
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String fullName;

    @Column
    private String thumbnail;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus status;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<TokenEntity> tokens = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ChatEntity> chats = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private Set<CartEntity> carts = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private Set<FavoriteEntity> favorites = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<OrderEntity> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
}
