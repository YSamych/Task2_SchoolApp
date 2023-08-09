package foxminded.ua.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foxminded.ua.model.Student;

public interface StudentDao extends JpaRepository<Student, Long> {
	
	Student findByFirstNameAndLastName(String firstName, String lastName);
	List<Student> findByCourses_CourseName(String courseName);
	List<Student> findByCourses_CourseId(int courseId);
	Student findById(int studentId);
}
