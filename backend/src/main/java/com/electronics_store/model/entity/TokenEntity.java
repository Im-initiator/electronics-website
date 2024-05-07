package com.electronics_store.model.entity;

import com.electronics_store.enums.TokenTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "token")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity extends BaseEntity{

    @Column(name = "access_token",nullable = false)
    private String accessToken;
    @Column(name = "refresh_token",nullable = false)
    private String refreshToken;
    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT false")
    private boolean expired;
    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT false")
    private boolean revoked;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenTypeEnum tokenType;

    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private AccountEntity account;
}
