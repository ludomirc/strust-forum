package com.qbit.forum.model;

/**
 * Created by Benek on 2015-07-15.
 */

import org.testng.annotations.Test;

import javax.persistence.EntityManager;

public class TestEntity {


    @Test()
    public void testEmailGenerator() {

        Privilege privilege = new Privilege();

        privilege.setDescription("test privileges");
        privilege.setPrivilegeId((short) 10);

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();

        em.persist(privilege);

        em.getTransaction().commit();
        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
