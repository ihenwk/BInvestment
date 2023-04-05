package com.ihe.UserInvestmentsService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "investments")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investments {

    @Id
    @GeneratedValue //Will auto-generate unique number for investmentId for investments SQL table
    private int investmentId;

    @Column
    private int userId;

    @Column
    private int shareId;

    @Column
    private int shareQuantity;
}
