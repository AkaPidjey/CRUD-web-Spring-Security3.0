package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {

    //--> New methods @link UserService interface

    void createNewUser(String name, String lastname, int age, String login,
                       String password, String role);
    void editUser(User user);
    void deleteUser(Long id);
    User getUser(Long id);
    List<User> getAllUsers();
    User getUserByLogin(String login);
    Role getRoleByName(String name);


    //--> Old methods @link UserService interface

//    List<User> index();
//    User show(long id);
//    void save(User user);
//    void update(long id, User user);
//    void delete(long id);
}
