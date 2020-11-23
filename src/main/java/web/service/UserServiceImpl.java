package web.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.DAO.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

//@Data
//@NoArgsConstructor

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //--> New methods @link UserService interface

    @Override
    public void createNewUser(String name, String lastname, int age, String login, String password, String role) {
        userDao.createNewUser(name, lastname, age, login, password, role);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }


    //--> Old methods @link UserService interface

//    @Override
//    public List<User> index() {
//        return userDao.index();
//    }
//
//    @Override
//    public User show(long id) {
//        return userDao.show(id);
//    }
//
//    @Override
//    public void save(User user) {
//        userDao.save(user);
//    }
//
//    @Override
//    public void update(long id, User user) {
//        userDao.update(id, user);
//    }
//
//    @Override
//    public void delete(long id) {
//        userDao.delete(id);
//    }
}
