package com.qbit.ejb.forumservice;

import com.qbit.ejb.forum.service.PrivilegeService;
import com.qbit.forum.model.Privilege;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Benek on 2015-07-17.
 */
@Stateless(name = "privilegeService", mappedName = "privilegeService")
@Remote(PrivilegeService.class)
@Local(PrivilegeService.class)
public class PrivilegeServiceImpl implements PrivilegeService {

    @PersistenceContext(unitName = "forum-persistence-unit", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public Privilege find(Long id) {
        return entityManager.find(Privilege.class, id);
    }

    public Collection<Privilege> getAll() {
        Query query = entityManager.createQuery("SELECT p from Privilege as p");
        return query.getResultList();
    }

    public void delete(Privilege privilege) {
        entityManager.remove(entityManager.contains(privilege) ? privilege : entityManager.merge(privilege));
    }

    public void add(Privilege privilege) {
        entityManager.persist(privilege);
    }

    public Privilege find(Privilege privilege) {
        return null;
    }
}
