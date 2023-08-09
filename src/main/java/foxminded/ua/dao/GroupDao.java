package foxminded.ua.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import foxminded.ua.model.Group;
import foxminded.ua.model.GroupInfo;

public interface GroupDao extends JpaRepository<Group, Long> {
	
	@Query("SELECT g.groupName, count(s) FROM Group g JOIN g.students s GROUP BY g.groupId HAVING COUNT (s.studentId) <= :amountOfStudents")
	List<GroupInfo> findAllGroupsWithAmountOfStudentsLessThanAndEqual(int amountOfStudents);

}
