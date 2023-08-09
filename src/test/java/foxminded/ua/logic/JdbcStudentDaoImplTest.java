package foxminded.ua.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import foxminded.ua.dao.CourseDao;
import foxminded.ua.dao.StudentDao;
import foxminded.ua.model.Student;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test-containers")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class JdbcStudentDaoImplTest {
	
	 	@Autowired
	 	private StudentService studentService;
	    private StudentDao studentDao;
	    private CourseDao courseDao;

	    @BeforeEach
	    void setUp() {
	    	studentService = new StudentService(studentDao, courseDao);
	    }
	    
	    @Nested
	    class DeleteStudentByIdTest {

	    	@Test
	    	void givenExistStudent_shouldRemoveStudent() {

	    		//given
	    		Student resultOfSearchingBeforeRemoving = studentDao.findById(1);

	    		//when
	    		studentService.deleteStudentById(1L);

	    		//then
	    		Student resultOfSearchingAfterRemoving = studentDao.findById(1);
	    		assertNull(resultOfSearchingBeforeRemoving != null);
	    		assertNull(resultOfSearchingAfterRemoving == null);
	    	}
	    	
	    	@Test
	    	void givenUnexistingStudent_shouldReturnFalse() {
	    		
	    		//when
	    		boolean result = studentService.deleteStudentById(135L);
	    		
	    		//then 
	    		assertFalse(result);
	    	}
	    }
	    
	    @Nested
	    class AddStudentTest {
	    	
	    	@Test
	    	void givenStudentFirstAndLastNames_shouldAddStudent() {
	    		
	    		//given
	    		Student resultOfSearchingBeforeAdding = studentDao.findById(19);
	    		
	    		//when
	    		studentService.addStudent("Grzegorz", "Brzynrzyszczykiewicz");
	    		
	    		//then
	    		Student resultOfSearchingAfterAdding = studentDao.findById(19);
	    		assertNull(resultOfSearchingBeforeAdding == null);
	    		assertNull(resultOfSearchingAfterAdding != null);
	    	}
	    }
	    
	    @Nested
	    class FindAllStudentRelatedToCourseTest {
	    	
	    	@Test
	    	void givenCourseName_shouldReturnAllStudentsRelatedToCourse() {
	    		
	    		//when
	    		List<Student> result = studentService.findAllStudentRelatedToCourse("History");
	    		
	    		//then
	    		assertEquals(1, result.size());
	    		assertEquals(2, result.get(0).getGroupId().intValue());
	    		assertEquals("Oliver", result.get(0).getFirstName());
	    		assertEquals("Miller", result.get(0).getLastName());
	    	}
	    	
	    	@Test
	    	void givenNotExistingCourseName_shouldReturnEmptyList() {
	    		
	    		//when
	    		List<Student> result = studentService.findAllStudentRelatedToCourse("History of ancient Rome");
	    		
	    		//then
	    		assertEquals(0, result.size());
	    	}
	    }
	    
	    @Nested
	    class AddStudentToCourseTest {
	    	
	    	@Test
	    	void givenStudentIdAndCourseId_shouldAddStudentToCourse() {
	    		
	    		//given
	    		List<Student> resultOfSearchBeforeAdding = studentService.findAllStudentRelatedToCourse("Java programming");
	    		
	    		//when
	    		int studentId = 1;
	    		int courseId = 10;
	    		studentService.addStudentToCourse(studentId, courseId);
	    		
	    		//then
	    		List<Student> resultOfSearchAfterAdding = studentService.findAllStudentRelatedToCourse("Java programming");
	    		assertEquals(3, resultOfSearchBeforeAdding.size());
	    		assertEquals(4, resultOfSearchAfterAdding.size());
	    	}
	    	
	    	@Test
	    	void givenWrongStudentIdAndRightCourseId_shouldReturnFalse() {
	    		
	    		//when
	    		int studentId = 30;
	    		int courseId = 2;
	    		boolean result = studentService.addStudentToCourse(studentId, courseId);
	    		
	    		//then
	    		assertFalse(result);
	    	}
	    }
	    
	    @Nested
	    class RemoveStudentFromCourse {
	    	
	    	@Test
	    	void givenStudentIdAndCourseId_shouldRemoveStudentFromCourse() {
	    		
	    		//given
	    		List<Student> resultOfSearchBeforeRemoving = studentService.findAllStudentRelatedToCourse("Java programming");
	    		
	    		//when
	    		int studentId = 11;
	    		int courseId = 10;
	    		studentService.removeStudentFromCourse(studentId, courseId);
	    		
	    		//then
	    		List<Student> resultOfSearchAfterRemoving = studentService.findAllStudentRelatedToCourse("Java programming");
	    		assertEquals(3, resultOfSearchBeforeRemoving.size());
	    		assertEquals(2, resultOfSearchAfterRemoving.size());
	    	}
	    	
	    	@Test
	    	void givenWrongStudentIdAndRightCourseId_shouldReturnFalse() {
	    		
	    		//when
	    		int studentId = 300;
	    		int courseId = 2;
	    		boolean result = studentService.removeStudentFromCourse(studentId, courseId);
	    		
	    		//then
	    		assertFalse(result);
	    	}
	    }
	    

}
