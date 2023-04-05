package com.ihe.shareservice.service;

import com.ihe.shareservice.entity.Shares;

import java.util.List;

public interface SharesService {


    List<Shares> getAllShares();

    Shares searchSharesByShareId(int shareId);
}
