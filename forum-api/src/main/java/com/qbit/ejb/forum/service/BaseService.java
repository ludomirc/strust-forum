package com.qbit.ejb.forum.service;

import java.util.Collection;

/**
 * Created by Benek on 2015-07-17.
 */
public interface BaseService<T> {

    public T findById(Long id);

    public void deleteById(Long id);

    public T findByObject(T object);

    public void deleteByObject(T object);

    Collection<T> getAll();

    void add(T object);

    void delete(T object);
}
