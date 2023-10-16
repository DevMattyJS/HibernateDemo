package com.msc.cruddemo.dao;

import com.msc.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Repository - provided by Spring for annotating DAO implementations
//             - Spring will automatically register the DAO implementation (thanks to component-scanning)
//             - Spring also provides translation for any JDBC related exceptions (from checked to unchecked)
@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    // @Transactional - provided by Spring framework
    //                - automatically start and end a transaction behind the scenes (no need to do it manually)
    //                - should be used just with queries, that changing data in our database
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    // No need for @Transactional, since we are not doing any update to DB
    @Override
    public Student findById(Integer id) {
        // entityManager.find(entity class, primary key)
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {

        // create a query
        // entityManager.createQuery(name of JPA entity (class name, not DB table name), entity class)
        TypedQuery<Student> query = entityManager.createQuery("FROM Student order by lastName", Student.class);

        // return query results
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {

        // create a query
        // :data => JPQL named parameter - a placeholder, which value will be set later
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:data", Student.class);

        // set query parameters - set value for JPQL named parameter
        query.setParameter("data", lastName);

        // return query result
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}
