package com.electronics_store.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ thông tin về các tính năng đặc biệt của sản phẩm
 */
@Entity
@Table(name = "salient_feature")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SalientFeatureEntity extends BaseEntity {
    @Column(columnDefinition = "NVARCHAR(150)")
    private String featureOne;

    @Column(columnDefinition = "NVARCHAR(150)")
    private String featureTwo;

    @Column(columnDefinition = "NVARCHAR(150)")
    private String featureThree;

    @Column(columnDefinition = "NVARCHAR(150)")
    private String featureFour;

    @OneToOne(mappedBy = "salientFeature")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
