package com.ihe.shareservice.persistence;

import com.ihe.shareservice.entity.Shares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharesDao extends JpaRepository <Shares, Integer> {
    @Query("SELECT s FROM shares s WHERE s.shareId =:id" )
    Shares searchSharesByShareId(@Param("id") int shareId);

}

