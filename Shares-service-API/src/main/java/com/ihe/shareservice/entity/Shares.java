package com.ihe.shareservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "shares")
@Table(name = "shares")
public class Shares {

    @Id
    private int shareId;
    @Column
    private String shareName;
    @Column
    private int shareUnitPrice;
}
