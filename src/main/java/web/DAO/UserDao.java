package web.DAO;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }


    public List<User> index() {
        return getEntityManager().createQuery("From User").getResultList();
    }

    public User show(int id) {
        return getEntityManager().find(User.class, id);
    }

    public void save(User user) {
        getEntityManager().persist(user);
    }

    public void update(int id, User updateUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updateUser.getName());
        userToBeUpdated.setLastname(updateUser.getLastname());
        userToBeUpdated.setAge(updateUser.getAge());
    }

    public void delete(int id) {
        getEntityManager().createQuery("delete from User where id=: id")
                .setParameter("id", id)
                .executeUpdate();
    }





//
//    @Override
//    public void add(User user) {
//        getEntityManager().persist(user);
//    }

//    @Override
//    public void edit(User user) {
//        getEntityManager().merge(user);
//    }

//    @Override
//    public List<User> allUsers() {
//        return getEntityManager().createQuery("From User").getResultList();
//    }

//    @Override
//    public User getById(int id) {
//        return getEntityManager().find(User.class, id);
//    }

//    @Override
//    public void delete(int id) {
//        getEntityManager().createQuery("delete from User where id=: id")
//                .setParameter("id", id)
//                .executeUpdate();
//    }
}
