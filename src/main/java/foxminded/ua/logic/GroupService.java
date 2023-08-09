package foxminded.ua.logic;

import java.util.List;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import foxminded.ua.dao.GroupDao;
import foxminded.ua.model.GroupInfo;
import jakarta.transaction.Transactional;

@Service
public class GroupService {
	//---------------------------------1.Find all groups with less or equal students’ number-------------------------//
	
	private final GroupDao group;
	
	private final Logger logger = LogManager.getLogger(GroupService.class);
	
	public GroupService(GroupDao group) {
		this.group = group;
	}
	
	
	
	@Transactional
	public List<GroupInfo> findAllGroupsWithAmountOfStudentsLessThanAndEqual(int amountOfStudents) {
		try {
			logger.info("Finding all groups with certain amount of students or below assigned to it: {}", amountOfStudents);
			return group.findAllGroupsWithAmountOfStudentsLessThanAndEqual(amountOfStudents);
        } catch (Exception e) {
        	logger.error(String.format("Failed during groups search with amount of students: %s", amountOfStudents), e);
        	System.out.println("Lack of group with such amount of students was found...");
            return Collections.emptyList();
        }
	}
}
