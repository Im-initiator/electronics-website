package com.electronics_store.model.entity;

import jakarta.persistence.*;

import com.electronics_store.enums.TokenType;

import lombok.*;

@Entity
@Table(name = "token")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity extends BaseEntity {

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean expired;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean revoked;

    @Column(name = "token_type", length = 20)
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;
}
