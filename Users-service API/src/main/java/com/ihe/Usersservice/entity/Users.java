package com.ihe.Usersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    private int userId;
    @Column
    private String companyName;
    @Column
    private String phoneNumber;
    @Column
    private String eMail;
    @Column
    private double balance;
    @Column
    private String userPassword;



}
