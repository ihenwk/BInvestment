package com.ihe.Usersservice.persistence;

import com.ihe.Usersservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UsersDao extends JpaRepository<Users,Integer> {
//JPQL Query for searching user by userId input
    @Query(value = "SELECT * FROM users WHERE userId =:id", nativeQuery = true)
    Users searchUsersByUserId(@Param("id") int userId);

    @Modifying
    @Transactional
    @Query(value = "insert into users values(:uid,:compName,:phoneNum,:email,:balance,:userPassword", nativeQuery = true)
    int addUser(@Param("uid") int userId, @Param("compName") String companyName, @Param("phoneNum") String phoneNumber,@Param("email") String eMail,@Param("balance")double balance,@Param("userPassword") String userPassword);

    @Transactional
    @Modifying
    @Query(value = "update users set balance=:balance +:inc where userId=:id", nativeQuery = true) // sort this out -how do i add balance and increment?
    int updateBalance(@Param("id") int userId, @Param("inc") double increment,@Param("balance") double balance);

    @Query(value ="SELECT * FROM users WHERE userId =:id and userPassword =:password", nativeQuery = true)
    Users findUserByUserIdAndPassword(@Param("id") int userId, @Param("password") String userPassword);
}
