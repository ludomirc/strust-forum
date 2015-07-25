package com.qbit.forum.model;

/**
 * Created by Benek on 2015-07-15.
 */

import com.qbit.test.Helper;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestEntity {

    protected EntityManager em;

    @BeforeClass
    private void setUp() {
        EntityManagerFactory emFactory = Helper.getEntityManagerFactory();
        em = emFactory.createEntityManager();
    }

    @AfterClass
    private void shutDown() {
        if (em.isOpen()) {
            em.close();
        }
    }

    @BeforeMethod
    private void setUpMethod() {
        em.getTransaction().begin();
    }

    @AfterMethod
    private void shotDownMethod() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }


    @Test(groups = "database-test")
    public void testRoleEntity() throws NoSuchFieldException {
        String expectedDescription = "testRoleDescription";
        String expectedName = "testRoleName";
        Short expectedRoeId = 10;

        Role role = new Role();
        role.setDescription(expectedDescription);
        role.setName(expectedName);
        role.setRoleId((short) expectedRoeId);

        persistAndCommit(role);

        List<Object> resultList = getListCueryByCriteria(Role.class, "roleId");

        assertEquals(resultList.size(), 1, "Return list size will be equal");
        role = (Role) resultList.get(0);

        assertEquals(role.getRoleId(), (short) expectedRoeId, "RoleId will be equal");
        assertEquals(role.getName(), expectedName, "Role name will be eagle");
    }

    private void persistAndCommit(Role role) {
        em.persist(role);
        em.flush();
    }


    @Test(groups = "database-test")
    public void testUerEntity() throws NoSuchFieldException {
        String expectedUsername = "testTserName";
        String expectedPassword = "test password";

        User user = new User();
        user.setUsername(expectedUsername);
        user.setPassword(expectedPassword);

        em.persist(user);
        em.flush();

        List<Object> resultList = getListCueryByCriteria(User.class, "username");

        assertEquals(resultList.size(), 1, "Return list size will be equel");
        user = (User) resultList.get(0);

        assertEquals(user.getUsername(), expectedUsername, "User name will be equal");
        assertNotNull(user.getPassword(), "Password can't bee null");
    }

    @Test(groups = "database-test")
    public void testPrivilegeEntity() throws NoSuchFieldException {
        String expectedDescription = "test privileges";
        Short expectedPrivilegeId = 10;
        Privilege privilege = new Privilege();

        privilege.setDescription(expectedDescription);
        privilege.setPrivilegeId((short) 10);

        em.persist(privilege);
        em.flush();

        List<Object> resultList = getListCueryByCriteria(Privilege.class, "privilegeId");

        assertEquals(resultList.size(), 1, "Return list size will be equel");
        privilege = (Privilege) resultList.get(0);

        assertEquals(privilege.getDescription(), expectedDescription, "Description will be equal");
        assertEquals(privilege.getPrivilegeId(), expectedPrivilegeId, "Description id will be equal");
    }

    private List<Object> getListCueryByCriteria(Class clazz, String filedName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Privilege> from = criteriaQuery.from(clazz);

        //select all records
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        select.equals(from.get(filedName));
        TypedQuery<Object> typedQuery = em.createQuery(select);
        return typedQuery.getResultList();
    }


}
