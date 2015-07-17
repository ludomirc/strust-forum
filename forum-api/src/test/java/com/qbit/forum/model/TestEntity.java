package com.qbit.forum.model;

/**
 * Created by Benek on 2015-07-15.
 */

import org.testng.annotations.*;

import javax.persistence.EntityManager;

public class TestEntity {

    protected EntityManager em;

    @BeforeClass
    public void setUp() {
        em = PersistenceManager.INSTANCE.getEntityManager();
    }

    @BeforeMethod
    private void setUpMethod() {
        em.getTransaction().begin();
    }

    @AfterMethod
    private void shotDownMethod() {
        em.getTransaction().commit();
    }

    @Test()
    public void testPrivilegeEntity() {

        Privilege privilege = new Privilege();

        privilege.setDescription("test privileges");
        privilege.setPrivilegeId((short) 10);

        em.persist(privilege);
    }

    @Test()
    public void testUserEntity() {
        User user = new User();

        user.setPassword("testPassword");
        user.setUsername("testUsername");

        em.persist(user);

    }

    @Test()
    public void testRoleEntity() {
        Role role = new Role();

        role.setDescription("testRoleDescription");
        role.setName("testRoleName");
        role.setRoleId((short) 10);

        em.persist(role);
    }

    @AfterClass
    public void shutDown() {
        em.close();
        PersistenceManager.INSTANCE.close();
    }

}
