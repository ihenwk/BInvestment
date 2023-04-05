package com.ihe.Usersservice.service;

import com.ihe.Usersservice.entity.Users;

import java.util.List;

public interface UsersService {
    List<Users> getAllUsers();
    public Users searchUsersByUserId(int userId);

    public boolean updateUserBalance(int id, double increment, double balance);
    Users loginCheck(int userId, String userPassword);
    public boolean addUser(Users users);

}
