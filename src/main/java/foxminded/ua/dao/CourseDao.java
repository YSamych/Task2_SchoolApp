package foxminded.ua.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import foxminded.ua.model.Course;

public interface CourseDao extends JpaRepository<Course, Long> {
	
	Optional<Course> findByCourseName(String courseName);
	void deleteCourseByCourseName(String courseName);
	Course findById(int courseId);
	

}
