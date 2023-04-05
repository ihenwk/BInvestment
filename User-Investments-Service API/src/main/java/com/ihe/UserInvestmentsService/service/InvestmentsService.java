package com.ihe.UserInvestmentsService.service;


import com.ihe.UserInvestmentsService.entity.Investments;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InvestmentsService {

    //produces list of all investments for all users
    public List<Investments>getAllInvestments();

    //produces list of all investments for a specific user
    public List <Investments> searchInvestmentsByUserId(int userId);

    //produces specific investment for user using userId and shareId
    public List<Investments>searchInvestmentsbyUserIdAndShareId(int userId, int shareId);

    //Buy shares - Creates investment record for a user
    public boolean addNewInvestment(Investments investments);

    // Update Existing shares - add to existing shares - increases amount of existing shares
    public boolean updateBuyExistingInvestmentsByUserIdAndShareId(int userId, int shareId, int increment);

    //Update Existing shares - Subtraction to existing shares - reduces amount of existing shares
    public boolean sellInvestmentsByUserIdAndShareId(int userId, int shareId, int decrement);

}
