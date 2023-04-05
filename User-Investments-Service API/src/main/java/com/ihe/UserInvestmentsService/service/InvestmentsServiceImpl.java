package com.ihe.UserInvestmentsService.service;

import com.ihe.UserInvestmentsService.entity.Investments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ihe.UserInvestmentsService.persistence.InvestmentsDao;

import java.util.List;

@Component
public class InvestmentsServiceImpl implements InvestmentsService {

    @Autowired
    InvestmentsDao investmentsDao;

    //produces list of all investments for all users
    @Override
    public List<Investments> getAllInvestments() {
        return investmentsDao.findAll();
    }

    //produces list of all investments for a specific user
    @Override
    public List<Investments> searchInvestmentsByUserId(int userId) {
        return investmentsDao.searchInvestmentsByUserId(userId);
    }

    @Override
    public List<Investments> searchInvestmentsbyUserIdAndShareId(int userId, int shareId) {
        return investmentsDao.investmentsByUserIdAndShareId(userId, shareId);
    }

    //Buy shares - Creates investment record for a user

    @Override
    public boolean addNewInvestment(Investments investments) {
        try {
            investmentsDao.save(investments);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    //Update Existing shares - Subtraction to existing shares - reduces amount of existing shares
    @Override
    public boolean sellInvestmentsByUserIdAndShareId(int userId, int shareId, int decrement) {
        Investments sellInvest = investmentsDao.searchInvestmentsByUserIdAndShareId(userId, shareId);
        if (sellInvest != null) {
            investmentsDao.userSellInvestments(userId, shareId, decrement);
            return true;
        } else {
            return false;
        }
    }


    // Update Existing shares - add to existing shares - increases amount of existing shares
    @Override
    public boolean updateBuyExistingInvestmentsByUserIdAndShareId(int userId, int shareId, int increment) {
        Investments invest = investmentsDao.searchInvestmentsByUserIdAndShareId(userId, shareId);
        if (invest != null) {
            investmentsDao.userBuyMoreInvestments(userId, shareId, increment);
            return true;
        } else {
            return false;
        }

    }
}









