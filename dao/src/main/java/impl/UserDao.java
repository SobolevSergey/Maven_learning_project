package impl;


import dao.BaseDao;
import model.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }

}
