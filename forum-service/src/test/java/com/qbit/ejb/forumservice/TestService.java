package com.qbit.ejb.forumservice;

import com.qbit.ejb.forum.service.PrivilegeService;
import com.qbit.forum.model.Privilege;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.util.List;
import java.util.Properties;

public class TestService {

    public void test() {

    }
    protected static final Logger log = Logger.getLogger(TestService.class);

    @Test()
    public void testPrivilege() throws Exception {
        log.info("---- start testPrivilege ----");
        final Properties p = new Properties();

        p.put("jdbc/forumDatasource", "new://Resource?type=DataSource");
        p.put("jdbc/forumDatasource.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("jdbc/forumDatasource.JdbcUrl", "jdbc:hsqldb:mem:testForumDb");

        final Context context = EJBContainer.createEJBContainer(p).getContext();

        PrivilegeService privilegeService = (PrivilegeService) context.lookup("java:global/forum-service/privilegeService");

        privilegeService.add(new Privilege((short) 1, "test 1"));
        privilegeService.add(new Privilege((short) 2, "test 2"));
        privilegeService.add(new Privilege((short) 3, "test 3"));

        List<Privilege> list = (List<Privilege>) privilegeService.getAll();
        log.info("---- testPrivilege delete privilege---- size" + list.size());
        for (Privilege privilege : list) {
            log.info(privilege.getDescription());
            privilegeService.delete(privilege);
        }
        context.close();

        // assertEquals("privilegeService.getAll()", 0, privilegeService.getAll().size());
    }
}