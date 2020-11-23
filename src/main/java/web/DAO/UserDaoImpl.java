package web.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //--> New methods @link UserDao interface

    @Override
    @Transactional
    public void createNewUser(String name, String lastname, int age, String login, String password, String role) {
        User user = new User(name, lastname, age, login, password);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(new HashSet<>());

        if (role.equals("ROLE_ADMIN")) {
            user.getRoles().add(getRoleByName("ROLE_ADMIN"));
            user.getRoles().add(getRoleByName("ROLE_USER"));
        } else {
            user.getRoles().add(getRoleByName("ROLE_USER"));
        }
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        user.setPasswordConfirm(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPasswordConfirm()));
        user.setRoles(getUserByLogin(user.getLogin()).getRoles());
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("From User").getResultList();
    }
    // option2 -->
//    List<User> listUsers = (List<User>) entityManager.createQuery("SELECT u FROM User u").getResultList();
//        return listUsers;

    @Override
    public User getUserByLogin(String login) {
        return getAllUsers().stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst().get();
    }

    @Override
    public Role getRoleByName(String name) {
        return (Role) entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name")
                .setParameter("name", name).getSingleResult();
    }


    //--> Old methods @link UserDao interface

//    @Override
//    public List<User> index() {
//        return entityManager.createQuery("From User").getResultList();
//    }
//
//    @Override
//    public User show(long id) {
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//    public void save(User user) {
//        entityManager.persist(user);
//    }
//
//    @Override
//    public void update(long id, User updateUser) {
//        User userToBeUpdated = show(id);
//        userToBeUpdated.setName(updateUser.getName());
//        userToBeUpdated.setLastname(updateUser.getLastname());
//        userToBeUpdated.setAge(updateUser.getAge());
//    }
//
//    @Override
//    public void delete(long id) {
//        entityManager.createQuery("delete from User where id=: id")
//                .setParameter("id", id)
//                .executeUpdate();
//    }


}
