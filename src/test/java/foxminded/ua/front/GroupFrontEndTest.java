package foxminded.ua.front;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import foxminded.ua.app.InputProcessor;
import foxminded.ua.logic.GroupService;

@SpringBootTest
@ActiveProfiles("mock")
public class GroupFrontEndTest {
	
	@InjectMocks
	GroupFrontEnd groupFront;
	@Mock
    private InputProcessor inputProcessor;
	@Mock
	GroupService groupDao;
	
	@Test
	void findAllGroupsWithNumberOfStudentsOptionOutputTest() {
		//given
		when(groupDao.findAllGroupsWithAmountOfStudentsLessThanAndEqual(anyInt())).thenReturn(Collections.emptyList());
		
		//when
		groupFront.findAllGroupsWithNumberOfStudentsOptionOutput();
		
		//then
		verify(groupDao, times(1)).findAllGroupsWithAmountOfStudentsLessThanAndEqual(anyInt());
	}

}
