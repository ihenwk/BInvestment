package com.ihe.shareservice.service;

import com.ihe.shareservice.entity.Shares;
import com.ihe.shareservice.persistence.SharesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SharesServiceImpl implements SharesService {

    @Autowired
    SharesDao sharesDao;

    @Override
    public List<Shares> getAllShares() {
        return sharesDao.findAll();
    }

    @Override
    public Shares searchSharesByShareId(int shareId) {
        return sharesDao.searchSharesByShareId(shareId);
    }
}

