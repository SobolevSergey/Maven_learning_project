package impl;

import model.User;
import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("baseUserService")
public class UserServiceImpl extends BaseServiceImpl<User, UserDao> implements UserService {
    private UserDao userDao;

    public UserServiceImpl(){

    }

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDao getDao() {
        return userDao;
    }
}
