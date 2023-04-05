package com.ihe.BrightInvestment.service;

import com.ihe.BrightInvestment.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

// rest template is used to consume other apis

@Service
public class BInvestmentServiceImpl implements BInvestmentService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BInvestment getInvestmentsByUserId(int userId) {
        // use investments-service api search user by Id method to get investment object
        BInvestment investments = restTemplate.getForObject("http://localhost:8082/investments/" + userId, BInvestment.class);
        return investments;
    }

    @Override
    public Users UserLoginCheck(int userId, String userPassword) {

        Users user = restTemplate.getForObject("http://localhost:8089/users/" + userId + "/" + userPassword, Users.class);
        // return this object - either a null or not null
        return user;
    }

    @Override
    public boolean CheckUserBalance(int userId) {
        // get user object using id argument through search by user id resource of users-service api
        Users user = restTemplate.getForObject("http://localhost:8089/users/" + userId, Users.class);
        // get balance field
        double balance = user.getBalance();
        // check if balance is sufficient to buy share
        if (balance >= 10000.00) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean UserSignUp(Users userToAdd) {
        //User userToAdd = new User(uid, pass, name, adr, phone, bal);
        // use the add user method of the user-service api - returns boolean
        Users addStatus = restTemplate.postForObject("http://localhost:8089/users/addUser", userToAdd, Users.class);
        // if method worked, return true if not return false
        if (addStatus != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean TopUpBalance(int userId, double increment) {
        // get user object using id argument through search by user id resource of user-service api
        Users userThen = restTemplate.getForObject("http://localhost:8089/users/" + userId, Users.class);
        if (userThen != null) { //if user exists,

            // top up balance with update user balance resource from user-service api via exchange method of restTemplate
            // set headers and entity for exchange method parameters
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>("worked", headers);
            String exchange = restTemplate.exchange("http://localhost:8089/users/" + userId + "/" + increment + "/" + userThen.getBalance() , HttpMethod.PUT, entity, String.class).getBody();

            // if the returned string from the rt.exchange method is "Balance Updated" (the return of the user-service top up balance resource when it works)
            if (exchange.equals("Balance Updated")) {
                return true;
            } else {
                return false;
            }
        }
        // if user does not exist, return false
        else {
            return false;
        }
    }
    /*
    @Override
    public boolean buyShare(int userId, int shareId, int increment, BInvestment investmentToAdd) {

        BInvestment investments = restTemplate.getForObject("http://localhost:8082/investments/" + userId + "/" + shareId, BInvestment.class);
        if (investments == null) {// if entry for shareId and userId does not exist then execute this code - this should lead to buy new share method in user-investment-api.
            BInvestment newInvestment = restTemplate.postForObject("http://localhost:8082/new", investmentToAdd, BInvestment.class);
            //BInvestment newInvestment = restTemplate.postForObject("http://localhost:8082/new", investmentToAdd, BInvestment.class);

            if (newInvestment != null) { // if new record successfully added return true. If not added then return false.
                return true;

            } else {
                return false;
            }

        }
        return false;
    }

     */

    @Override
    public boolean buyShare(Investments investmentToAdd){
        //User userToAdd = new User(uid, pass, name, adr, phone, bal);
        // use the add user method of the user-service api - returns boolean
        Investments newInvestment = restTemplate.postForObject("http://localhost:8082/investments/new/",investmentToAdd, Investments.class);
        // if method worked, return true if not return false
        if (newInvestment != null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean buyMoreSharesForExistingShares(int userId, int shareId, int increment) {
        BInvestment investments = restTemplate.getForObject("http://localhost:8082/investments/" + userId + "/" + shareId, BInvestment.class);
        if (investments != null) {// if entry for shareId and userId exists then execute this code - this should lead to update buy share method
            // update record in investments table in SQL. Use userId, shareId and increment(no. of shares user wants to buy) to make change to share quantity. This method should update buy more existing shares resource from user-investment-service api via exchange method of restTemplate
            // set headers and entity for exchange method parameters
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>("worked", headers);
            String exchange = restTemplate.exchange("http://localhost:8082/investments/" + userId + "/" + shareId + "/" + increment, HttpMethod.PUT, entity, String.class).getBody();
            return true;

            // need to work on this
        } else {
            return false;
        }
    }

    @Override
    public boolean sellShare(int userId, int shareId, int decrement) {

        BInvestment investments = restTemplate.getForObject("http://localhost:8082/investments/" + userId + "/" + shareId, BInvestment.class);

        if (investments != null) {// if entry for shareId and userId exists then execute this code - this should lead to update buy share method
            // update record in investments table in SQL. Use userId, shareId and decrement (no. of shares user wants to sell) to make change to share quantity. This method should update sell existing share resource from user-investment-service api via exchange method of restTemplate
            // set headers and entity for exchange method parameters
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>("worked", headers);
            String exchange = restTemplate.exchange("http://localhost:8082/investment/" + userId + "/" + shareId + "/" + decrement, HttpMethod.PUT, entity, String.class).getBody();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Shares GetShareDetails(int shareId) {
        Shares share = restTemplate.getForObject("http://localhost:8088/Shares/"+ shareId, Shares.class);
        return share;
    }

    @Override
    public Investments checkInvestmentExists(int userId, int shareId) {

       Investments investments = restTemplate.getForObject("http://localhost:8082/investments/" + userId + "/" + shareId, Investments.class);
        // return this object - either a null or not null
        return investments;
    }
    @Override
    public Investments getExistingInvestments(int userId) {
        Investments investments = restTemplate.getForObject("http://localhost:8082/investments/" + userId, Investments.class);
        // return this object - either a null or not null
        return investments;
    }

    @Override
    public List<BInvestment> viewAllUserInvestments(int userId) {

        List<BInvestment> bInvestmentList = new ArrayList<BInvestment>();

        //calling investments service and storing investments in investment list
        InvestmentsList investmentsList = restTemplate.getForObject("http://localhost:8082/investments/" + userId, InvestmentsList.class);

        for (Investments investment:investmentsList.getInvestments()) {

            //for every shareId pick every share Name and share Unit price
            Shares share = restTemplate.getForObject("http://localhost:8088/Shares/" + investment.getShareId(), Shares.class);

            //calculate total amount invested in a particular share
                double totalAmountInvestedInShares = share.getShareUnitPrice() * investment.getShareQuantity();

            //creating BInvestment Object
            BInvestment bInvestment = new BInvestment(share.getShareName(), share.getShareUnitPrice(), investment.getShareQuantity(), totalAmountInvestedInShares);

            //add bInvestment object in list
            bInvestmentList.add(bInvestment);
        }
        return bInvestmentList;

    }

    }


