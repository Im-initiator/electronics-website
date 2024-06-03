package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ tin nhắn của người dùng nhắn cho cửa hàng
 */
@Entity
@Table(name = "chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private BranchEntity branch;
}
