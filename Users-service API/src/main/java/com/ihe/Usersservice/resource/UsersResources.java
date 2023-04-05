package com.ihe.Usersservice.resource;

import com.ihe.Usersservice.entity.Users;
import com.ihe.Usersservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersResources {

    @Autowired
    private UsersService usersService;

    @RequestMapping(path = "/users",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Users>findAllUsersResource(){
        return usersService.getAllUsers();
    }

    //Resource to search by userId
    @RequestMapping(path = "/users/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Users searchUsersByUserIdResource(@PathVariable("uid") int userId){
        return usersService.searchUsersByUserId(userId);
    }

    //Resource to add user
    @RequestMapping(path="/users/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users addUserResource(@RequestBody Users users){
        if (usersService.addUser(users))
            return users;
        else
            return null;

    }

    //Resource for update balance
    @RequestMapping(path ="/users/{id}/{increment}/{balance}" ,method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateUserBalanceResource ( @PathVariable("id") int userId, @PathVariable("increment") double increment, @PathVariable("balance") double balance){
        if (usersService.updateUserBalance(userId,increment,balance)){
            return "Balance has been updated";
        } else {
            return "Balance has not been updated";
        }
    }

    //Resource for login-in check
    @RequestMapping(path = "/users/{userId}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Users loginCheck(@PathVariable("userId") int userId, @PathVariable("password") String userPassword){
        return usersService.loginCheck(userId,userPassword);
    }

}
