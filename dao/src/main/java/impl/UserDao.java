package impl;


import dao.BaseDao;
import model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }


    @Override
    protected String getIdColumnName() {
        return "login";
    }

}
