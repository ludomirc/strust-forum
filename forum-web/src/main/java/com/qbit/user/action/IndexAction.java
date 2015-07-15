package com.qbit.user.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

;

/**
 * Created by Benek on 2015-07-13.
 */
public class IndexAction extends ActionSupport {

    private static final Logger logger = Logger.getLogger(IndexAction.class);

    // all struts logic here
    public String execute() {
        logger.debug("in execute");
        return "redirectToLogin";

    }
}
