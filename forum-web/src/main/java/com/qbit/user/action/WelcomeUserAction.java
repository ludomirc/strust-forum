package com.qbit.user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.qbit.ejb.forumservice.ForumService;

import javax.ejb.EJB;

public class WelcomeUserAction extends ActionSupport {

    @EJB(name = "ForumService")
    ForumService forumService;

    private String username;
    private String password;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // all struts logic here
    public String execute() {

        username = forumService.getHallWordForum();
        return "SUCCESS";

    }
}