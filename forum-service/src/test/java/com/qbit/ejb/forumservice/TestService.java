package com.qbit.ejb.forumservice;

import com.qbit.ejb.forum.service.PrivilegeService;
import com.qbit.forum.model.Privilege;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.util.List;
import java.util.Properties;

public class TestService extends TestCase {

    protected static final Logger log = Logger.getLogger(TestService.class);

    public void testPrivilege() throws Exception {
        final Properties p = new Properties();

        p.put("forumDatabase", "new://Resource?type=DataSource");
        p.put("forumDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("forumDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");

        final Context context = EJBContainer.createEJBContainer().getContext();

        PrivilegeService privilegeService = (PrivilegeService) context.lookup("java:global/forum-service/PrivilegeService");

        privilegeService.add(new Privilege((short) 1, "test 1"));
        privilegeService.add(new Privilege((short) 2, "test 2"));
        privilegeService.add(new Privilege((short) 3, "test 3"));

        List<Privilege> list = (List<Privilege>) privilegeService.getAll();

        for (Privilege privilege : list) {
            log.debug(privilege.getDescription());
            privilegeService.delete(privilege);
        }
        context.close();

        assertEquals("privilegeService.getAll()", 0, privilegeService.getAll().size());
    }
}