package com.msc.cruddemo;

import com.msc.cruddemo.dao.StudentDAO;
import com.msc.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

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
//			createStudent(studentDAO);
//			createMultipleStudents(studentDAO);
//			readStudent(studentDAO, 3);
//			queryForStudents(studentDAO);
			queryForStudentsByLastName(studentDAO, "Doe");
		};
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO, String lastName) {

		// get a list of students
		List<Student> students = studentDAO.findByLastName(lastName);
		// display students
		for(Student student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		// get a list of students
		List<Student> students = studentDAO.findAll();
		// display students
		for(Student student : students) {
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO, int studentId) {

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with the id: " + studentId);
		Student student = studentDAO.findById(studentId);

		if (student == null) {
			System.out.printf("Student with id %d wasn't found in DB.\n", studentId);
			return;
		}

		// display the student
		System.out.println("Student found: " + student);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create list of new students
		System.out.println("Creating list of student objects ...");
		List<Student> students = Arrays.asList(
				new Student("Jane", "Doe", "jane.d@email.com"),
				new Student("Mary", "Public", "mary.p@email.com"),
				new Student("Loki", "Atreus", "loki@midgard.com")
		);

		// iterate through list of students and save each one of them to a DB
		System.out.println("Saving students ...");
		for (Student student : students) {
			studentDAO.save(student);
		}
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
