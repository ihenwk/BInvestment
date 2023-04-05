package com.ihe.BrightInvestment.controller;

import com.ihe.BrightInvestment.entity.*;
import com.ihe.BrightInvestment.service.BInvestmentService;
import com.ihe.BrightInvestment.service.BInvestmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//A view is used to display data using the model class object.
@Controller
public class BrightInvestmentController {

    @Autowired
    BInvestmentService bInvestmentService;

    // ================================CONTROLLER TO DISPLAY WELCOME PAGE ==============================================
    @RequestMapping("/")
    public ModelAndView UserLoginInputController() {
        return new ModelAndView("WelcomePage");
    }

    // ===============================CONTROLLER TO DISPLAY SIGN IN PAGE ===============================================
   @RequestMapping("/signIn")
    public  ModelAndView SignInButtonController() {
        return new ModelAndView("SignInPage");
    }

    // =========================CONTROLLER TO DISPLAY SIGN UP PAGE =====================================================
    @RequestMapping("/signUp")
    public  ModelAndView SignUpButtonController() {
        return new ModelAndView("SignUpPage");
    }

    // ==================CONTROLLER TO DISPLAY DEPOSIT FUNDS PAGE ======================================================
    @RequestMapping("/depositFunds")
    public  ModelAndView DepositFundsController() {
        return new ModelAndView("DepositFunds");
    }

    // =====================CONTROLLER TO DISPLAY BUY SHARES OPTIONS PAGE ==============================================

    @RequestMapping("/buyingShares")
    public ModelAndView buyingSharesFirstPageController(){return new ModelAndView("BuyingShares");}

    // =======================CONTROLLER TO DISPLAY BUY NEW SHARES PAGE  ===============================================

    @RequestMapping("/buyingNewShares")
    public ModelAndView buyNewSharesPageController(){return new ModelAndView("BuyNewShares","inv", new Investments());}

    // ==================CONTROLLER TO DISPLAY BUY MORE EXISTING SHARES PAGE  ==========================================

    @RequestMapping("/buyingExistingShares")
    public ModelAndView buyMoreExistingSharesPageController(){return new ModelAndView("BuyMoreExistingShares");}


    // ============================CONTROLLER TO DISPLAY SELL SHARE PAGE================================================
    @RequestMapping("/sellSharesPage")
    public  ModelAndView SellSharesPageController() {
        return new ModelAndView("SellShares");
    }

    // =====================CONTROLLER TO DISPLAY VIEW ALL SHARES FOR USER INPUT PAGE ==================================
    @RequestMapping("/viewAllUserSharesInput")
    public  ModelAndView ViewAllUserSharesInputPageController() {
        return new ModelAndView("ViewAllUserInvestmentsInputPage");
    }

    // =====================CONTROLLER TO DISPLAY VIEW ALL SHARES FOR USER OUTPUT PAGE =================================
    @RequestMapping("/viewAllUserSharesOutput")
    public  ModelAndView ViewAllUserSharesOutputPageController() {
        return new ModelAndView("ViewAllInvestmentsOutputPage");
    }

    // =======================CONTROLLER FOR USER SIGN UP ONCE ON SIGN UP PAGE==========================================

    @RequestMapping("/addUser")
    public ModelAndView UserAddController(@RequestParam("userId") int userId, @RequestParam("companyName") String companyName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("eMail") String eMail, @RequestParam("balance") double balance, @RequestParam("userPassword") String userPassword, HttpSession session) {
        // create empty Model and view
        ModelAndView modelAndView = new ModelAndView();
        // create user to be added to table in database of user-service api
        Users userToAdd = new Users(userId, companyName, phoneNumber, eMail, balance, userPassword);
        // perform service method for user sign up
        boolean addStatus = bInvestmentService.UserSignUp(userToAdd);

        if (addStatus == true) {
            modelAndView.addObject("message","Successfully Signed Up, you are now Logged In!");
            session.setAttribute("user", userToAdd);
            modelAndView.setViewName("MainMenu");
        }
        else {
            modelAndView.addObject("message", "Failed to Sign Up, User already exists! Please try logging in or signing up with different details");
            session.setAttribute("user", new Users());
            modelAndView.setViewName("WelcomePage");
        }

        return modelAndView;
    }


    // ===================================CONTROLLER FOR CHECKING USER LOGIN ===========================================
    @RequestMapping("/checkUser")
    public ModelAndView checkUserLoginController(@RequestParam(value ="userId", required = true) int userId, @RequestParam(value ="userPassword", required = true) String userPassword,  HttpSession session) {
        // create empty MAV
        ModelAndView modelAndView = new ModelAndView();

        // run login-check method of service
        Users logUser = bInvestmentService.UserLoginCheck(userId, userPassword);


        // if the customer login check is passed
        if (logUser != null) {
            // add the customer object to MAV
            modelAndView.addObject("user", logUser);
            // set attribute in session
            session.setAttribute("user", logUser);  // store an object in session by allotting a unique string to the object. Later, By using the same string this object can be accessed from the session until the session is active.
            // show the Main Menu view on the web app
            modelAndView.setViewName("MainMenu"); // Used to set the name of the view to be resolved by the configured ViewResolver
        }
        // if login check is failed
        else {
            // display message on selected view
            modelAndView.addObject("message", "Unable to Login, User Details Incorrect.");
            // reset customer object for another login try
            modelAndView.addObject("user", new Users());
            // show the Login page on the web app
            modelAndView.setViewName("WelcomePage");
        }

        return modelAndView;

    }

    @RequestMapping("/logOut")
    public ModelAndView logOutController() {
        // create empty MAV
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "You have successfully Logged Out!");
        modelAndView.setViewName("WelcomePage");
        return modelAndView;
    }

    // ==================================CONTROLLER FOR CHECKING USER FUNDS ============================================
    @RequestMapping("/checkBalance")
    public ModelAndView checkBalanceController(HttpSession session) {
        // create empty MAV
        ModelAndView modelAndView = new ModelAndView();

        Users userObj = (Users) session.getAttribute("userId");  //get value from the session
        int userId = userObj.getUserId();
        double userBalance = userObj.getBalance();
        // use service method (which user user-service api) to check user balance
        boolean balanceStatus = bInvestmentService.CheckUserBalance(userId);

        if (balanceStatus == true) {
            modelAndView.addObject("message","Your Balance is Sufficient, it is currently : £"+userBalance);
            modelAndView.setViewName("MainMenu");
        }
        else {
            modelAndView.addObject("message","Your Balance is Insufficient, it is currently : £"+userBalance+" please Top Up!");
            modelAndView.setViewName("MainMenu");
        }

        return modelAndView;

    }

    // ====================CONTROLLERS FOR UPDATING/DEPOSITING USER FUNDS FOR INVESTMENTS =============================
    @RequestMapping("/topUpBalanceInputPage")
    public ModelAndView topUpBalanaceInputController() {
        return new ModelAndView("InputTopUp");
    }

    @RequestMapping("/topUpBalance")
    public ModelAndView topUpBalanceController(@RequestParam("topUp") double topUp, HttpSession session) {
        // create empty MAV
        ModelAndView modelAndView = new ModelAndView();
        // use session to get user object of logged in user and hence their userId
        Users userObj = (Users) session.getAttribute("user");
        int userId = userObj.getUserId();
        double oldBalance = userObj.getBalance();
        // perform top up balance method of service (which uses update user balance method of user-service api)
        boolean toppedUp = bInvestmentService.TopUpBalance(userId, topUp);
        // re-set the user object balance field
        userObj.setBalance(oldBalance+topUp);

        if (toppedUp == true) {
            modelAndView.addObject("message","You have successfully deposited £"+topUp);
            modelAndView.setViewName("MainMenu");
        }
        else {
            modelAndView.addObject("message","Failed to deposit funds.Please Try Again.");
            modelAndView.setViewName("MainMenu");
        }

        return modelAndView;
    }



//=====================================CONTROLLER FOR BUYING NEW SHARES ================================================

@RequestMapping("/buyingShares/buyNewShares")
public ModelAndView BuyNewShareController(@RequestParam("userId") int userId,@ModelAttribute("inv") Investments investmentsToAdd, BindingResult errors, HttpSession session){
    ModelAndView modelAndView = new ModelAndView();

      //Users userObj = (Users) session.getAttribute("userId");  //get value from the session

        Investments checkInvestment = bInvestmentService.checkInvestmentExists(investmentsToAdd.getUserId(), investmentsToAdd.getShareId());
        modelAndView.addObject("investments", investmentsToAdd);

        Investments addInvestment = new Investments(userId,investmentsToAdd.getShareId(), investmentsToAdd.getShareQuantity());
        boolean addStatus = bInvestmentService.buyShare(addInvestment);

        String message = null;

        if (checkInvestment == null) {

            if (addStatus == true) {
                modelAndView.addObject("message", "Successfully bought share!");
                session.setAttribute("investment", investmentsToAdd);
                modelAndView.setViewName("MainMenu");
            } else {
                modelAndView.addObject("message", "Failed to buy share! Please try again!");
                session.setAttribute("investment", new Investments());
                modelAndView.setViewName("MainMenu");
            }

        } else {

            modelAndView.addObject("message", "Failed to buy share! Please try again!");
            session.setAttribute("investment", new Investments());
            modelAndView.setViewName("MainMenu");

        }
        return modelAndView;
    }

    //===================================Controllers to buy more of existing shares ===================================
@RequestMapping("/buyingShares/buyMoreOfExistingShares")
public ModelAndView buyMoreOfExistingSharesController(@RequestParam("userId") int userId,@RequestParam("shareId") int shareId, @RequestParam("inc") int shareQuantity, HttpSession session){
// eed to be able to see shares they have already invested in and pick from drop down box.
    //They can then pick a quantity they want to buy and this should update investments table through investmnet api
    // create empty MAV
// need to be able to view shares for user and then they pick from drop down box what shares they want to sell.
        // create empty MAV
        ModelAndView modelAndView = new ModelAndView();

        Users userObj = (Users) session.getAttribute("userId");  //get value from the session
        boolean buyMoreStatus = bInvestmentService.buyMoreSharesForExistingShares(userId,shareId, shareQuantity);

        if (buyMoreStatus == true){
            modelAndView.addObject("message", "Successfully sold share!");
            modelAndView.setViewName("MainMenu");
        } else {
            modelAndView.addObject("message", "Share has not been sold!");
            modelAndView.setViewName("SellShares");
        }

        return modelAndView;
}

    //==========================================Controllers to sell shares ============================================
@RequestMapping("/sellShares")
    public ModelAndView sellSharesController(@RequestParam("userId") int userId,@RequestParam("shareId") int shareId, @RequestParam("dec") int shareQuantity, HttpSession session){
// need to be able to view shares for user and then they pick from drop down box what shares they want to sell.
    // create empty MAV
    ModelAndView modelAndView = new ModelAndView();

    Users userObj = (Users) session.getAttribute("userId");  //get value from the session
    boolean sellStatus = bInvestmentService.sellShare(userId,shareId, shareQuantity);

        if (sellStatus == true){
            modelAndView.addObject("message", "Successfully sold share!");
            modelAndView.setViewName("MainMenu");
        } else {
            modelAndView.addObject("message", "Share has not been sold!");
            modelAndView.setViewName("SellShares");
        }

    return modelAndView;

}

    //=============================Controllers to view all shares purchased by user  ==================================
    @RequestMapping("/viewAllInvestments")
    public ModelAndView viewAllSharesController(@RequestParam("userId") int userId, HttpSession session){
// want to show the share name, number of shares user has invested in
        ModelAndView modelAndView = new ModelAndView();

        List<BInvestment> bInvestments = bInvestmentService.viewAllUserInvestments(userId);

        modelAndView.addObject("Investments", bInvestments);

        modelAndView.setViewName("ViewAllInvestmentsOutputPage");

        return modelAndView;
    }

}
