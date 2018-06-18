package impl;

import dao.BaseDao;
import model.Model;

public class SimpleDao<T extends Model> extends BaseDao<T> {

    public SimpleDao(){
        super(null);
    }
}
