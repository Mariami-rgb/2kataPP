package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactory();
    private final String CREATE_TABLE_SQL = "create TABLE IF NOT EXISTS Users(id BIGINT NOT NULL AUTO_INCREMENT, name Varchar(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))";
    private final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS Users";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createSQLQuery(CREATE_TABLE_SQL).addEntity(User.class);
        query.executeUpdate();
        tx.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createSQLQuery(DROP_TABLE_SQL).addEntity(User.class);
        query.executeUpdate();
        tx.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        tx.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.delete(session.get(User.class, id));
        tx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<User> users = session.createQuery("from User").getResultList();
        tx.commit();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete from User");
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}
