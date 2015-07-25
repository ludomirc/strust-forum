package com.qbit.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

/**
 * Created by Benek on 2015-07-25.
 */
public class Helper {

    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("forum-persistence-unit");
    }
}
