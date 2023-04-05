package com.ihe.BrightInvestment.service;

import com.ihe.BrightInvestment.entity.BInvestment;
import com.ihe.BrightInvestment.entity.Investments;
import com.ihe.BrightInvestment.entity.Shares;
import com.ihe.BrightInvestment.entity.Users;

import java.util.List;

public interface BInvestmentService {

    BInvestment getInvestmentsByUserId(int userId);

    boolean buyShare(Investments investmentToAdd);

    //boolean buyShare(int userId, int shareId, int increment);
    //boolean buyShare(int userId, int shareId, int increment, BInvestment investment);

    boolean buyMoreSharesForExistingShares(int userId, int shareId, int increment);

    boolean sellShare(int userId, int shareId, int decrement);

    Users UserLoginCheck(int userId, String userPassword);

    boolean UserSignUp(Users userToAdd);

    boolean CheckUserBalance(int userId);

    boolean TopUpBalance(int userId, double increment);

    Shares GetShareDetails(int shareId);

    Investments checkInvestmentExists(int userId, int shareId);

    Investments getExistingInvestments(int userId);

    List<BInvestment> viewAllUserInvestments (int userId);

}
