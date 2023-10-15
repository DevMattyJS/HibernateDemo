package com.msc.cruddemo.dao;

import com.msc.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }
}
