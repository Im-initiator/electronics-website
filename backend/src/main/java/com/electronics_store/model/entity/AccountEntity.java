package com.electronics_store.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity extends BaseEntity{
    @Column(unique = true,nullable = false)//throw DataIntegrityViolationException
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true,nullable = false)
    private String email;
    @Column
    private String fullName;
    @Column
    private String address;
    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT false")
    private boolean status;
    @ManyToMany
    @JoinTable(name = "account_role",joinColumns = @JoinColumn(name = "account_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<TokenEntity> tokens;

}
