package foxminded.ua.logic;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import foxminded.ua.dao.CourseDao;
import foxminded.ua.dao.StudentDao;
import foxminded.ua.model.Course;
import foxminded.ua.model.Student;
import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	private final StudentDao studentDao;
	private final CourseDao courseDao;
	private final Logger logger = LogManager.getLogger(StudentService.class);
	
	public StudentService(StudentDao student, CourseDao courseDao){
		this.studentDao = student;
		this.courseDao = courseDao;
	}
	
	@Transactional
	public void addStudent(String firstName, String lastName) {
		try {
			logger.info("Adding student to DB with name: {} / last name: {}", firstName, lastName);
			studentDao.save(new Student(firstName, lastName));
		} catch(Exception e) {
			logger.error(String.format("Failed during adding student with name: %s and last name: %s to DB", firstName, lastName), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
		}
	}

	@Transactional
	public boolean studentExists(String firstName, String lastName) {
		Student studentDataFromDB = studentDao.findByFirstNameAndLastName(firstName, lastName);
		if(studentDataFromDB.getFirstName().equals(firstName) && studentDataFromDB.getLastName().equals(lastName)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean deleteStudentById(Long studentId) {
		try {
			logger.info("Removing student from DB by id: {}", studentId);
			Student studentToDelete = studentDao.findById(studentId).get();
			if(studentToDelete != null) {
				studentDao.delete(studentToDelete);
				System.out.println("Student with student id: " + studentId + " was deleted...");
				System.out.println("For return to main menu put 0(zero)");
				return true;
			} else {
				System.out.println("Such student does not exist....");
				System.out.println("For return to main menu put 0(zero)");
				return false;
			}
		} catch(Exception e) {
			logger.error(String.format("Failed during removing student from DB by id: %s", studentId), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
			return false;
		}
	}

	@Transactional
	public List<Student> findAllStudentRelatedToCourse(String courseName) {
		try {
			logger.info("Finding all students related to a course with name: {}", courseName);
			return studentDao.findByCourses_CourseName(courseName);
		} catch (Exception e) {
			logger.error(String.format("Failed during finding students related to a course with name: %s", courseName), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
			return Collections.emptyList();
		}
	}
	
	@Transactional
	public boolean addStudentToCourse(int studentId, int courseId) {
		try {
			logger.info("Adding student {} to a course: {}", studentId, courseId);
			
			Student student = studentDao.findById(studentId);
			Course course = courseDao.findById(courseId);
			student.getCourses().add(course);
			course.getStudents().add(student);
			studentDao.save(student);
			courseDao.save(course);
			
			System.out.println("Student with ID: " + studentId + " was assigned to a course with ID: " + courseId);
			System.out.println();
			System.out.println("For return to main menu put 0(zero)");
				
			return true;
		} catch (Exception e) {
			logger.error(String.format("Failed during adding student: %s to a course : %s", studentId, courseId), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
			return false;
		}
	}
	
	@Transactional
	public boolean removeStudentFromCourse(int studentId, int courseId) {
		try {
			logger.info("Removing student: {} from a course: {}", studentId, courseId);
			
			Student student = studentDao.findById(studentId);
			Course course = courseDao.findById(courseId);
			student.getCourses().remove(course);
			course.getStudents().remove(student);
			studentDao.save(student);
			courseDao.save(course);
			
			System.out.println("Student with ID: " + studentId + " was removed from a course with ID: " + courseId);
			System.out.println();
			System.out.println("For return to main menu put 0(zero)");
			return true;
		}  catch (Exception e) {
			logger.error(String.format("Failed during removing student: %s from course : %s", studentId, courseId), e);
			System.out.println("Something went wrong.....");
			System.out.println("For return to main menu put 0(zero)");
			return false;
		}
	}
}
