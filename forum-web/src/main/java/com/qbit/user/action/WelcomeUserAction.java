package com.qbit.user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.qbit.ejb.forum.service.HalloWordService;
import org.apache.log4j.Logger;

import javax.ejb.EJB;

public class WelcomeUserAction extends ActionSupport {

    private static final Logger log = Logger.getLogger(WelcomeUserAction.class);

    @EJB(name = "HalloWordService")
    HalloWordService halloWordService;

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
        log.debug(this.toString());
        if (password.equals(username)) {
            log.debug("return : " + SUCCESS);
            return SUCCESS;
        } else {
            log.debug("return : " + ERROR);
            return ERROR;
        }

    }

    @Override
    public String toString() {
        return "WelcomeUserAction{" +
                "  username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}