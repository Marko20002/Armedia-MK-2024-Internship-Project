package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


public abstract class EntityDao<T> {
    private final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public EntityDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Optional<T> save(T entity) {
        entityManager.persist(entity);
        return Optional.of(entity);
    }

    public T find(Object id) {
        return entityManager.find(entityClass, id);

    }
}