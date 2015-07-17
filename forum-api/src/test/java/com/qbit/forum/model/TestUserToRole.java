package com.qbit.forum.model;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

/**
 * Created by Benek on 2015-07-17.
 */
public class TestUserToRole {

    private static final Logger log = Logger.getLogger(TestUserToRole.class);
    protected EntityManager em;

    @BeforeClass
    private void setUp() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("test-persistence-unit");
        em = emFactory.createEntityManager();
    }

    @AfterClass
    private void shutDown() {
        if (em.isOpen()) {
            em.close();
        }
    }


    @Test(groups = "database-test")
    public void TestUserToRole() throws InterruptedException {
        log.info("------start TestUserToRole test------");

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        User user = new User();
        user.setPassword("testPassword");
        user.setUsername("testUsername");
        em.persist(user);
        em.flush();

        Role role = new Role();
        role.setDescription("testDesc");
        role.setRoleId((short) 10);
        role.setName("test role");
        em.persist(role);
        em.flush();


        user = em.find(User.class, user.id);
        role = em.find(Role.class, role.id);

        log.info(user);
        log.info(role);

        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<Role>());
        }
        user.getRoles().add(role);

        transaction.commit();
        log.info("------finish TestUserToRole test------");
    }
}
