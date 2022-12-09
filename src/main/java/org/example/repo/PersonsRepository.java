package org.example.repo;

import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;
import org.example.entity.Hotel;
import org.example.entity.PersonsEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PersonsRepository {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void createPerson() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        PersonsEntity personsEntity = new PersonsEntity();
        personsEntity.setName("John");
        personsEntity.setAge(13);
        session.persist(personsEntity);
        session.getTransaction().commit();
    }
//
//    public static PersonsEntity getById(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        PersonsEntity personsEntity = session.get(PersonsEntity.class, id);
//        return personsEntity;
//    }
//
    public static void updatePerson(int id, String name, int age) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        PersonsEntity person = session.load(PersonsEntity.class, id);
        person.setName(name);
        person.setAge(age);
        session.update(person);

        session.getTransaction().commit();
    }

    public static Stream<PersonsEntity> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from PersonsEntity where id=2", PersonsEntity.class)
                    .stream();
            ///////////////////    select * from persons where id=2;
        }
    }

    public static String getPersonsNames() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select distinct u.name from PersonsEntity u where u.id > 2";
            Query<String> query = session.createQuery(hql, String.class);
            return query.uniqueResult();
        }
    }

    public static void update() {
        try (Session session = sessionFactory.openSession()) {
            session.createQuery("update PersonsEntity set age=age+1 where id=2",
                    PersonsEntity.class);
        }
    }

    public static Hotel getAllHotel() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Hotel where id= :id", Hotel.class).uniqueResult();
        }
    }

    public static List<Hotel> getExample(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Hotel where name = :name";
            Query<Hotel> query = session.createQuery(hql, Hotel.class);
            query.setParameter("name", name);
            return query.list();
        }
    }


    public static void main(String[] args) {

        updatePerson(0,"Mars", 50);
    }
}
