package dao;

import model.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public abstract class BaseDao<T extends Model> {
    @PersistenceContext(unitName = "ExampleDS")
    protected EntityManager entityManager;

    private Class<T> entityClass;

    public BaseDao() {
        entityClass = null;
    }

    public BaseDao(Class<? extends T> entityClass) {
        this.entityClass = (Class<T>) entityClass;
    }

    public void setEntityClass(Class<? extends T> entityClass) {
        this.entityClass = (Class<T>) entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }


    public Collection<T> getAll() {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        TypedQuery<T> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    public T getById(Serializable id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(getIdColumnName()), id));
        TypedQuery<T> q = entityManager.createQuery(criteriaQuery);
        return q.getSingleResult();
    }

    @Transactional
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public T update(T entity) {
        if(Objects.nonNull(entity)) {
            T result = entityManager.merge(entity);
            return result;
        }
        System.out.println("Merge Error");
        return null;
    }

    @Transactional
    public boolean delete(T entity) {
        entityManager.remove(entity);
        return true;
    }

    public boolean deleteById(Serializable pk)
    {
        T entity = getById(pk);
        return delete(entity);
    }


    protected String getIdColumnName() {
        return "id";
    }
}