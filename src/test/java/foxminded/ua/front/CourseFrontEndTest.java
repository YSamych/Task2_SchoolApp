package foxminded.ua.front;

import foxminded.ua.app.InputProcessor;
import foxminded.ua.logic.CourseService;
import foxminded.ua.model.Course;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("mock")
@SpringBootTest
public class CourseFrontEndTest {

    @InjectMocks
    private CourseFrontEnd courseFrontEnd;

    @Mock
    private InputProcessor inputProcessor;

    @Mock
    private CourseService courseRepo;

    @Test
    void courseInfoOptionOutputTest() {
        //given
        when(courseRepo.findByName(anyString())).thenReturn(Optional.empty());

        //when
        courseFrontEnd.courseInfoOptionOutput("test_course_name");

        //then
        verify(courseRepo).findByName(anyString());
    }

    @Test
    void runAddCourseOptionTest() {
        //when
        courseFrontEnd.runAddCourseOption();

        //then
        verify(inputProcessor, times(2)).getStringInput();
    }
    
    @Test
    void runUpdateCourseNameOptionTest() {
    	//when
    	courseFrontEnd.runUpdateCourseNameOption();
    	
    	//then
    	verify(inputProcessor, times(2)).getStringInput();
    }
    
    @Test
    void runUpdateCourseDescriptionOptionTest() {
    	//when
    	courseFrontEnd.runUpdateCourseDescriptionOption();
    	
    	//then
    	verify(inputProcessor, times(2)).getStringInput();
    }
    
    @Test
    void runDeleteCourseOptionTest() {
    	//when
    	courseFrontEnd.runDeleteCourseOption();
    	
    	//then
    	verify(inputProcessor, times(1)).getStringInput();
    }
}
