package com.ihe.UserInvestmentsService.resource;

import com.ihe.UserInvestmentsService.entity.Investments;
import com.ihe.UserInvestmentsService.entity.InvestmentsList;
import com.ihe.UserInvestmentsService.service.InvestmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvestmentsResources {

    @Autowired
    InvestmentsService investmentsService;

    // Resource to get all investments for all users - works in postman
    @RequestMapping(path = "/investments",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody
    public List<Investments> findAllInvestmentsResource() {
       return investmentsService.getAllInvestments();
    }

    // Resource for search investments by userId - works in postman
    @RequestMapping(path = "/investments/{iid}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
           public InvestmentsList searchInvestmentsByUserIdResource(@PathVariable("iid") int userId){
               return new InvestmentsList((List<Investments>) investmentsService.searchInvestmentsByUserId(userId));
           }

    //Resource to search for investments by userId and shareId - works in postman
    @RequestMapping(path = "/investments/{iid}/{sid}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public InvestmentsList searchInvestmentsByUserIdResource(@PathVariable("iid") int userId, @PathVariable("sid") int shareId){
        return new InvestmentsList((List<Investments>) investmentsService.searchInvestmentsbyUserIdAndShareId(userId,shareId));
    }

    //resource to completely add new investment
    @RequestMapping(path="/investments/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Investments addInvestmentResource(@RequestBody Investments investments){
        if (investmentsService.addNewInvestment(investments))
            return investments;
        else{
            return null;
        }

    }
    // resource to buy more of existing investments by userId and shareId - works in postman
    @RequestMapping(path = "/investments/{uid}/{iid}/{inc}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
    public String addToExistingInvestmentsForUserResource(@PathVariable("uid") int userId,@PathVariable("iid") int shareId, @PathVariable("inc") int increment){
        if (investmentsService.updateBuyExistingInvestmentsByUserIdAndShareId(userId,shareId,increment))
            return "Existing Share for user has been incremented";
        else
            return "Existing share for use has not been updated/incremented";

}
    // resource to sell existing investments by userId and shareId - works in postman
    @RequestMapping(path = "/investment/{uid}/{iid}/{dec}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
    public String sellExistingInvestmentsForUserResource(@PathVariable("uid") int userId,@PathVariable("iid") int shareId, @PathVariable("dec") int decrement){
        if (investmentsService.sellInvestmentsByUserIdAndShareId(userId,shareId,decrement))
            return "Existing Share for user has been sold";
        else
            return "Existing share for use has not been updated/decremented";
    }

    }


