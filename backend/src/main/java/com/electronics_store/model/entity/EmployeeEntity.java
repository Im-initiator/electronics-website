package com.electronics_store.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lưu trữ thông tin của nhân viên  của một chi nhánh nào đó
 */
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeEntity extends BaseEntity {

    @Column(columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column(columnDefinition = "NVARCHAR(20)",nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate dateBirth;

    @Column(nullable = false)
    private Double salary;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private BranchEntity branch;
}
