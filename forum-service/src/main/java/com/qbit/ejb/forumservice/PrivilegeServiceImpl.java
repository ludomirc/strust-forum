package com.qbit.ejb.forumservice;

import com.qbit.ejb.forum.service.HalloWordService;
import com.qbit.ejb.forum.service.PrivilegeService;
import com.qbit.forum.model.Privilege;

import javax.ejb.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

/**
 * Created by Benek on 2015-07-17.
 */
@Stateless(name = "PrivilegeService", mappedName = "PrivilegeService")
@Remote(PrivilegeService.class)
@Local(PrivilegeService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PrivilegeServiceImpl implements PrivilegeService {

    @PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public Privilege find(Long id) {
        return entityManager.find(Privilege.class, id);
    }

    @Override
    public Collection<Privilege> getAll() {
        Query query = entityManager.createQuery("SELECT p from Privilege as p");
        return query.getResultList();
    }

    @Override
    public Privilege find(Privilege privilege) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Privilege> q = cb.createQuery(Privilege.class);
        Root<Privilege> c = q.from(Privilege.class);
        ParameterExpression<Short> privilegeId = cb.parameter(Short.class);
        q.select(c).where(cb.equal(c.<Number>get("privilegeId"), privilegeId));

        TypedQuery<Privilege> query = entityManager.createQuery(q);
        query.setParameter(privilegeId, privilege.getPrivilegeId());
        List<Privilege> results = query.getResultList();

        return ((Queue<Privilege>) results).poll();
    }

    @Override
    public void add(Privilege privilege) {
        entityManager.persist(privilege);
    }

    @Override
    public void delete(Privilege privilege) {
        entityManager.remove(privilege);
    }
}
