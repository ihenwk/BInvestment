package com.ihe.BrightInvestment.resource;

import com.ihe.BrightInvestment.entity.*;
import com.ihe.BrightInvestment.service.BInvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class BInvestmentResource {

    @Autowired
    BInvestmentService bInvestmentService;

    @GetMapping(path = "/BrightInvestment/login/{uid}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users UserLoginCheckResource(@PathVariable("uid") int userId, @PathVariable("password") String userPassword) {
        return bInvestmentService.UserLoginCheck(userId, userPassword);

    }

    @PostMapping(path = "/BrightInvestment/Users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean UserSignUpResource(@RequestBody Users user) {
        return bInvestmentService.UserSignUp(user);

    }

    @GetMapping(path = "/BrightInvestment/check/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean CheckUserBalanceResource(@PathVariable("uid") int userId) {
        return bInvestmentService.CheckUserBalance(userId);
    }

    @PutMapping(path = "/BrightInvestment/top/{uid}/{inc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean TopUpBalanceResource(@PathVariable("uid") int userId, @PathVariable("inc") double increment) {
        return bInvestmentService.TopUpBalance(userId, increment);
    }

    @PostMapping(path = "/BrightInvestment/buyNew", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean buyShareResource(@RequestBody Investments investmentToAdd) {
        return bInvestmentService.buyShare(investmentToAdd);
    }

    @PutMapping(path = "/BrightInvestment/{uid}/{iid}/{inc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean buyMoreSharesForExistingSharesResource(@PathVariable("uid") int userId, @PathVariable("iid") int shareId, @PathVariable("inc") int increment, BInvestment investment) {
        return bInvestmentService.buyMoreSharesForExistingShares(userId, shareId, increment);
    }

    @PutMapping(path = "/BrightInvestment/{uid}/{iid}/{dec}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean sellShareResource(@PathVariable("uid") int userId, @PathVariable("iid") int shareId, @PathVariable("dec") int decrement) {
        return bInvestmentService.sellShare(userId, shareId, decrement);
    }

    @GetMapping(path = "/BrightInvestment/Shares/{sid}", produces = MediaType.APPLICATION_JSON_VALUE)
    Shares findShareByIdResource(@PathVariable("shareId") int shareId) {
        return bInvestmentService.GetShareDetails(shareId);
    }

    @GetMapping(path = "/BrightInvestment/investments/{uid}/{sid}", produces = MediaType.APPLICATION_JSON_VALUE)
    Investments findShareByShareIdAndUserIdResource(@PathVariable("uid") int userId, @PathVariable("sid") int shareId) {
        return bInvestmentService.checkInvestmentExists(userId, shareId);
    }

    @GetMapping(path = "/BrightInvestment/investments/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    Investments findAllUserInvestmentsResource(@PathVariable("uid") int userId) {
        return bInvestmentService.getExistingInvestments(userId);
    }

    @GetMapping(path ="/BrightInvestment/UserInvestments/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BInvestmentList getAllUserInvestmentsResource(@PathVariable ("uid") int userId){
        return new BInvestmentList((bInvestmentService.viewAllUserInvestments(userId)));

    }
}

