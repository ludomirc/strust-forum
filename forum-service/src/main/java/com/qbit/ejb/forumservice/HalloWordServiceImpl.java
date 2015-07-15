package com.qbit.ejb.forumservice;

import com.qbit.ejb.forum.service.HalloWordService;

import javax.ejb.*;

/**
 * Created by Benek on 2015-07-03.
 */
@Stateless(name = "HalloWordService", mappedName = "HalloWordService")
@Remote(HalloWordService.class)
@Local(HalloWordService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class HalloWordServiceImpl implements HalloWordService {


    private static String message = "hello Forum default message";

    public String getHallWordForum() {
        return message;
    }

    public void setHellWordForum(String message) {
        this.message = message;
    }
}
