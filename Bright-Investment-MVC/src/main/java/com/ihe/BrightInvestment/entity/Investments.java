package com.ihe.BrightInvestment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investments{

    private int userId;
    private int shareId;
    private int shareQuantity;
}
