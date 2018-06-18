package impl;

import dao.BaseDao;
import model.Model;
import service.BaseService;

import java.io.Serializable;
import java.util.Collection;

public abstract class BaseServiceImpl<T extends Model, Dao extends BaseDao<T>> implements BaseService<T, Dao> {

    @Override
    public Collection<T> getAll() {
        return getDao().getAll();
    }

    @Override
    public T getById(Serializable id) {
        return getDao().getById(id);
    }

    @Override
    public T update(T object) {
        return getDao().update(object);
    }

    @Override
    public boolean deleteById(Serializable object) {
        return getDao().deleteById(object);
    }
}
