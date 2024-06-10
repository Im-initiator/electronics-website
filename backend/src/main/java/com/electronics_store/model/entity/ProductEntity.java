package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ thông tin sản phẩm
 */
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quality;

    @Column
    private String thumbnail;

    @Column
    private Float rate;

    @Column
    private Integer sellNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "salient_feature_id")
    private SalientFeatureEntity salientFeature;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_sale_id")
    private ProductSaleEntity productSale;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<CartEntity> carts = new HashSet<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<FavoriteEntity> favorites = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderEntity> orders = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductSpecification> productSpecifications = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SpecialSaleEntity> specialSales = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ColorEntity> colors = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImageEntity> images = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private BranchEntity branch;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductTypeEntity productType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;
}
