package foxminded.ua.logic;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import foxminded.ua.dao.GroupDao;
import foxminded.ua.model.GroupInfo;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test-containers")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class JdbcGroupDaoImplTest {
	
	@Autowired
    private GroupService groupService;
    private GroupDao groupDao;

    @BeforeEach
    void setUp() {
    	groupService = new GroupService(groupDao);
    }
    
    @Test
    void givenNumberOfStudents_shouldReturnAllGroupsWithGivenAmountOfStudentsOrLess() {
    	
    	//when
    	List<GroupInfo> result = groupDao.findAllGroupsWithAmountOfStudentsLessThanAndEqual(6);
    	
    	//then
    	Optional<GroupInfo> groupOpt1 = result.stream().filter(group-> 6 == group.getAmountOfStudents()).findAny();
    	assertTrue(groupOpt1.isPresent());
    	GroupInfo group1 = groupOpt1.get();
    	assertEquals(6, group1.getAmountOfStudents());
    	assertEquals(2, result.size());
    }

}
