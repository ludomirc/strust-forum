package com.qbit.ejb.forumservice;

import javax.ejb.*;

/**
 * Created by Benek on 2015-07-03.
 */
@Stateless(name = "ForumService" , mappedName = "ForumService")
@Remote(ForumService.class)
@Local(ForumService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ForumServiceImpl implements ForumService {


    private static String message = "hello Forum default message";

    public String getHallWordForum() {
        return message;
    }

    public void setHellWordForum(String message) {
        this.message = message;
    }
}
