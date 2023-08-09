package foxminded.ua.front;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import foxminded.ua.app.InputProcessor;
import foxminded.ua.logic.StudentService;

@SpringBootTest
@ActiveProfiles("mock")
public class StudentFrontEndTest {
	
	@InjectMocks
	StudentFrontEnd studentFront;
	@Mock
    private InputProcessor inputProcessor;
	@Mock
	StudentService studentDao;
	
	@Test
	void runAddNewStudentOptionTest() {
		//when
		studentFront.runAddNewStudentOption();
		
		//then
		verify(inputProcessor, times(2)).getStringInput();
	}
	
	@Test
	void runDeleteStudentByIdOptionTest() {
		//when
		studentFront.runDeleteStudentByIdOption();
		
		//then
		verify(inputProcessor, times(1)).getIntInput();
	}
	
	@Test
	void findAllStudentsRelatedToCourseOptionOutputTest() {
		//when
		studentFront.findAllStudentsRelatedToCourseOptionOutput();
		
		//then
		verify(inputProcessor, times(1)).getStringInput();
	}
	
	@Test
	void runAddStudentToCourseOptionTest() {
		//when
		studentFront.runAddStudentToCourseOption();
		
		//then
		verify(inputProcessor, times(2)).getIntInput();
	}
	
	@Test
	void runRemoveStudentFromCourseOptionTest() {
		//when
		studentFront.runRemoveStudentFromCourseOption();
		
		//then
		verify(inputProcessor, times(2)).getIntInput();
	}

}
