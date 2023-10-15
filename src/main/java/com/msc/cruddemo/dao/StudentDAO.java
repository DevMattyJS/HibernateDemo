package com.msc.cruddemo.dao;

import com.msc.cruddemo.entity.Student;

public interface StudentDAO {

    void save(Student student);

    Student findById(Integer id);
}
