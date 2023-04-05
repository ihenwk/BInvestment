package com.ihe.BrightInvestment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shares {

    private int shareId;
    private String shareName;
    private int shareUnitPrice;
}
