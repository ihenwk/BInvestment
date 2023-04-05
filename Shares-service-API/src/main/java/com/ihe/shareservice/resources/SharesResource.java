package com.ihe.shareservice.resources;

import com.ihe.shareservice.entity.Shares;
import com.ihe.shareservice.entity.SharesList;
import com.ihe.shareservice.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SharesResource {

    @Autowired
    private SharesService sharesService;

   @RequestMapping (path = "/Shares", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shares> findAllSharesResources(){
        return sharesService.getAllShares();
    }


    // Resource to search shares table using shareId
@RequestMapping(path ="/Shares/{sid}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Shares searchSharesBySharesIdResources (@PathVariable("sid") int shareId){
        return sharesService.searchSharesByShareId(shareId);

}
    }


