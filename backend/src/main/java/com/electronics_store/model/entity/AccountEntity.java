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
    @Column(unique = true, nullable = false, length = 50) // throw DataIntegrityViolationException
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String fullName;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus status;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TokenEntity> tokens = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChatEntity> chats = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<CartEntity> carts = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<FavoriteEntity> favorites = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<OrderEntity> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
}
