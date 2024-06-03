package com.electronics_store.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ tên thông số kĩ thuật cho mỗi thể loại sản phẩm ví dụ: điện thoại thì có Ram...
 */
@Entity
@Table(name = "technical_specification")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalSpecificationEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "technicalSpecification", cascade = CascadeType.ALL)
    private Set<ProductSpecification> productSpecifications = new HashSet<>();
}
