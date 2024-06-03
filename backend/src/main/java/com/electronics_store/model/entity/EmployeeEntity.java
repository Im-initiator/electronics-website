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

    @Column(columnDefinition = "NVARCHAR(20)")
    private String phone;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private LocalDate dateBirth;

    @Column
    private Double salary;

    @OneToOne(cascade = CascadeType.PERSIST)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;
}
