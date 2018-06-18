package service;

import dao.BaseDao;
import model.Model;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService<E extends Model,Dao extends BaseDao<E>> {
    Collection<E> getAll();
    E getById(Serializable id);
    E update(E object);
    boolean deleteById(Serializable pk);

    Dao getDao();
}
