package com.qbit.user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.qbit.ejb.forumservice.ForumService;

import javax.ejb.EJB;

public class WelcomeUserAction extends ActionSupport {

    @EJB(name = "ForumService")
    ForumService forumService;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // all struts logic here
    public String execute() {

        username = forumService.getHallWordForum();
        return "SUCCESS";

    }
}