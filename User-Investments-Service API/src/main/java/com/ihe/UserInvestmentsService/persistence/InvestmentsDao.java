package com.ihe.UserInvestmentsService.persistence;

import com.ihe.UserInvestmentsService.entity.Investments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
public interface InvestmentsDao extends JpaRepository<Investments, Integer> {



    @Query(value = "SELECT * FROM investments WHERE userId=:id", nativeQuery = true)
    List<Investments> searchInvestmentsByUserId(@Param("id") int userId);

    // find shares a user is invested in via userId and shareId
    @Query(value = "SELECT * FROM investments WHERE userId=:id and shareId=:shareId", nativeQuery = true)
    List <Investments> investmentsByUserIdAndShareId(@Param("id") int userId, @Param("shareId") int shareId);

    // find shares a user is invested in via userId and shareId
    @Query(value = "SELECT * FROM investments WHERE userId=:id and shareId=:shareId", nativeQuery = true)
    Investments searchInvestmentsByUserIdAndShareId(@Param("id") int userId, @Param("shareId") int shareId);


    //Update shares:if user chooses to add to shares they already have
    @Modifying
    @Transactional
    @Query("update Investments set shareQuantity=shareQuantity +:inc where userId=:userId and shareId=:shareId")
    int userBuyMoreInvestments(@Param("userId") int userId,@Param("shareId") int shareId,@Param("inc") int increment);

// Update shares: Decrement shares when company sells shares/stock
    @Modifying
    @Transactional
    @Query("update Investments set shareQuantity=shareQuantity-:dec where userId=:userId and shareId=:shareId")
    int userSellInvestments(@Param("userId") int userId,@Param("shareId") int shareId,@Param("dec") int decrement);


}
