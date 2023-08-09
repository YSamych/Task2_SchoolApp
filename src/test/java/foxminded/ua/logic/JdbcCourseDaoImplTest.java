package foxminded.ua.logic;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import foxminded.ua.dao.CourseDao;
import foxminded.ua.model.Course;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test-containers")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class JdbcCourseDaoImplTest {

	@Autowired
	private CourseService courseService;
    private CourseDao courseDao;

    @BeforeEach
    void setUp() {
    	courseService = new CourseService(courseDao);
    }
    
    @Nested
    class FindByNameTest {
    	
    	@Test
    	void givenCourseName_shouldReturnCourseInfo() {
    		
    		//when
    		String courseName = "Chemistry";
    		Optional<Course> resultCourse = courseService.findByName(courseName);
    		
    		//then
    		assertTrue(resultCourse.isPresent());
    		assertEquals(7, resultCourse.get().getCourseId().intValue()); //course id check
    		assertEquals("Chemistry", resultCourse.get().getCourseName()); // course name check
    		assertEquals("The study of substances", resultCourse.get().getCourseDescription()); //course description check
    	}
    	
    	@Test
    	void givenIncorrectCourseName_shouldReturnNull() {
    		
    		//when
    		String courseName = "Chemistr";
    	
    		//then
    		Assertions.assertEquals(Optional.empty(), courseService.findByName(courseName));
    	}
    }
    
    @Nested
    class AddCourseTest {
    	
    	@Test
    	void givenCourseNameAndDescription_shouldAddCourseToDB() {
    		
    		//given
    		Optional<Course> resultOfSearchBeforeAdding = courseService.findByName("New Course");
    		
    		//when
    		courseService.addCourse("New Course", "New knowlege");
    		
    		//then
    		Optional<Course> resultOfSearchAfterAdding = courseService.findByName("New Course");
    		assertTrue(resultOfSearchBeforeAdding.isEmpty());
    		assertFalse(resultOfSearchAfterAdding.isEmpty());
    	}
    }
    
    @Nested
    class UpdateCourseNameTest {
    	
    	@Test
    	void givenCourseNameAndNewCourseName_shouldChangeTheName() {
    		
    		//given
    		String courseNameBeforeChanging = courseDao.findById(8).toString();
    		
    		//when
    		String oldCourseName = "History";
    		String newCourseName = "History of Rome";
    		courseService.updateCourseName(oldCourseName, newCourseName);
    		
    		//then
    		String courseNameAfterChanging = courseDao.findById(8).toString();
    		assertEquals("History", courseNameBeforeChanging);
    		assertEquals("History of Rome", courseNameAfterChanging);
    	}
    	
    	@Test
    	void givenWrongCourseNameOfExistingCourseAndNewCourseName_theNameShouldRemainPrevious() {
    		
    		//given
    		String courseNameBeforeChanging = courseDao.findById(8).toString();
    		
    		//when
    		String oldCourseName = "Hisory";
    		String newCourseName = "History of Rome";
    		courseService.updateCourseName(oldCourseName, newCourseName);
    		
    		//then
    		String courseNameAfterChanging = courseDao.findById(8).toString();
    		assertEquals("History", courseNameBeforeChanging);
    		assertEquals("History", courseNameAfterChanging);
    	}
    }
    
    @Nested
    class UpdateCourseDescriptionTest {
    	
    	@Test
    	void givenCourseNameAndNewCourseDescription_shouldSetNewCourseDescription() {
    		
    		//given
    		String courseDescriptionBeforeChanging = courseService.findByName("History").get().getCourseDescription().toString();
    		
    		//when
    		String courseName = "History";
    		String newCourseDescription = "The systematic study and documentation of human activity and something else";
    		courseService.updateCourseDescription(courseName, newCourseDescription);
    		
    		//then
    		String courseDescriptionAfterChanging = courseService.findByName("History").get().getCourseDescription().toString();
    		assertEquals("The systematic study and documentation of human activity", courseDescriptionBeforeChanging);
    		assertEquals("The systematic study and documentation of human activity and something else", courseDescriptionAfterChanging);
    	}
    	
    	@Test
    	void givenWrongCourseNameOfExistingCourseAndNewCourseDescription_courseDescriptionShouldRemainPrevious() {
    		
    		//given
    		String courseDescriptionBeforeChanging = courseService.findByName("History").get().getCourseDescription().toString();
    		
    		//when
    		String courseName = "Hisory";
    		String newCourseDescription = "The systematic study and documentation of human activity and something else";
    		courseService.updateCourseDescription(courseName, newCourseDescription);
    		
    		//then
    		String courseDescriptionAfterChanging = courseService.findByName("History").get().getCourseDescription().toString();
    		assertEquals("The systematic study and documentation of human activity", courseDescriptionBeforeChanging);
    		assertEquals("The systematic study and documentation of human activity", courseDescriptionAfterChanging);
    	}
    }
    
    @Nested
    class DeleteCourseTest {
    	
    	@Test
    	void givenCourseName_shouldDeleteCourseFromDB() {
    		
    		//given
    		Optional<Course> resultOfSearchBeforeDeleting = courseService.findByName("History");
    		
    		//when
    		String courseName = "History";
    		courseService.deleteCourse(courseName);
    		
    		//then
    		Optional<Course> resultOfSearchAfterDeleting = courseService.findByName("History");
    		assertFalse(resultOfSearchBeforeDeleting.isEmpty());
			assertTrue(resultOfSearchAfterDeleting.isEmpty());

    	}
    	
    	@Test
    	void givenWrongCourseName_shouldNotDeleteCourseFromDB() {
    		
    		//given
    		Optional<Course> resultOfSearchBeforeDeleting = courseService.findByName("History");
    		
    		//when
    		String courseName = "Hitory";
    		courseService.deleteCourse(courseName);
    		
    		//then
    		Optional<Course> resultOfSearchAfterDeleting = courseService.findByName("History");
    		assertFalse(resultOfSearchBeforeDeleting.isEmpty());
    		assertFalse(resultOfSearchAfterDeleting.isEmpty());
    	}
    }
    
	
}
