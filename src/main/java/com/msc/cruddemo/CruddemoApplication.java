package com.msc.cruddemo;

import com.msc.cruddemo.dao.StudentDAO;
import com.msc.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// CommandLineRunner - from a SpringBoot framework
	// This code will be executed after the spring beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {

		// create new student object
		System.out.println("Creating new student ...");
		Student tempStudent = new Student("John", "Doe", "jd@email.com");

		// save student object
		System.out.println("Saving student ...");
		studentDAO.save(tempStudent);

		// display id of saved student
		System.out.println("Student saved. Generated id: " + tempStudent.getId());
	}

}
