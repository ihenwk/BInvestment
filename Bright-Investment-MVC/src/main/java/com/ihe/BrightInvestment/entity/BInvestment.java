package com.ihe.BrightInvestment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BInvestment {


    private String shareName;
    private int shareUnitPrice;
    private int shareQuantity;
    private double totalAmountInShares;

}
