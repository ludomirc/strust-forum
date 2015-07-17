package com.qbit.ejb.forum.service;

import com.qbit.forum.model.BaseModel;

import java.util.Collection;

/**
 * Created by Benek on 2015-07-17.
 */
public interface BaseService<T extends BaseModel> {

    public T find(Long id);

    Collection<T> getAll();

    public T find(T object);

    void add(T object);

    void delete(T object);
}
