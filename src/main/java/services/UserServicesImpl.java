package services;

import Dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class UserServicesImpl implements UserServices{

    private final UserDao userDao;

    @Autowired
    public UserServicesImpl(UserDao userDao){
        this.userDao=userDao;
    }

    @Transactional
    public List<User> index() {
        return userDao.index();
    }

    @Transactional
    public User show(Long id) {
        return userDao.show(id);
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    public void update(User user, Long id) {
        userDao.update(user,id);
    }
}