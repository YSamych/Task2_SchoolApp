package foxminded.ua.logic;

import java.util.Optional;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import foxminded.ua.app.SchoolApp;
import foxminded.ua.dao.CourseDao;
import foxminded.ua.model.Course;
import jakarta.transaction.Transactional;

@Service
public class CourseService {
	
	private CourseDao courseDao;
    
    private final Logger logger = LogManager.getLogger(CourseService.class);

    public CourseService(CourseDao course) {
        this.courseDao = course;
    }

	@Transactional
	public Optional<Course> findByName(String courseName) {
		try {
			logger.info("Finding course by name: {}", courseName);
			return courseDao.findByCourseName(courseName);
		} catch (Exception e) {
			System.out.println("Something went wrong.....");
			logger.error(String.format("Failed to find course by name: %s", courseName), e);
			return Optional.empty();
		}
    }

	@Transactional
	public void addCourse(String courseName, String courseDescription) {
		try {
			logger.info("Adding course to DB: {}", courseName, courseDescription);
			Course courseToAdd = new Course(courseName, courseDescription);
			courseDao.save(courseToAdd);
		} catch (Exception e) {
			logger.error(String.format("Failed to add course with parameters: course name %s and description %s to DB", courseName, courseDescription), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
		} 
	}

	@Transactional
	public void updateCourseName(String courseName, String newName) {
		try {
			logger.info("Updating course name: {}", courseName);
			Optional<Course> courseToUpdate = courseDao.findByCourseName(courseName);
			if(courseToUpdate.isPresent()) {
				courseToUpdate.get().setCourseName(newName);
				courseDao.save(courseToUpdate.get());
			} else {
				SchoolApp.clearConsoleImitation();
				System.out.println("Course was not found...");
			}
		} catch (Exception e) {
			logger.error(String.format("Fialed during course name update: %s", courseName), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
		}
	}

	@Transactional
	public void updateCourseDescription(String courseName, String courseDescription) {
		try {
			logger.info("Updating course description: {}", courseDescription);
			Optional<Course> courseToUpdate = courseDao.findByCourseName(courseName);
			if(courseToUpdate.isPresent()) {
				courseToUpdate.get().setCourseDescription(courseDescription);
				courseDao.save(courseToUpdate.get());
			} else {
				SchoolApp.clearConsoleImitation();
				System.out.println("Course was not found...");
			}
			
		} catch (Exception e) {
			logger.error(String.format("Failed during course description update: %s", courseDescription), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
		}
	}

	@Transactional
	public void deleteCourse(String courseName) {
		try {
			logger.info("Removing course from DB: {}", courseName);
			if(courseDao.findByCourseName(courseName).isPresent()) {
				courseDao.deleteCourseByCourseName(courseName);
			} else {
				SchoolApp.clearConsoleImitation();
				System.out.println("Course was not found...");
			}
		} catch (Exception e) {
			logger.error(String.format("Failed during removing course from DB: %s", courseName), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
		}
	}
}
