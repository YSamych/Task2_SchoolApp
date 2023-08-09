package foxminded.ua.front;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import foxminded.ua.app.InputProcessor;
import foxminded.ua.app.SchoolApp;
import foxminded.ua.dao.GroupDao;
import foxminded.ua.logic.GroupService;
import foxminded.ua.model.Group;
import foxminded.ua.model.GroupInfo;

@Service
public class GroupFrontEnd {
	
	private final InputProcessor inputProcessor;
	private final GroupService groupService;
	public GroupFrontEnd(InputProcessor inputProcessor, GroupService groupService) {
		this.inputProcessor = inputProcessor;
		this.groupService = groupService;
	}
	
	public void runFindAllGroupsWithNumberOfStudentsOption() throws SQLException { 
		findAllGroupsWithNumberOfStudentsOptionView();
		findAllGroupsWithNumberOfStudentsOptionOutput();
	}
	
	private void findAllGroupsWithNumberOfStudentsOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|       >>>1.Find all groups with less or equal students’ number<<<      |");
		System.out.println("|                                                                        |");
		System.out.println("|Please, enter amount below                                              |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	public void findAllGroupsWithNumberOfStudentsOptionOutput() {
		List<GroupInfo> groupsFromSQL = groupService.findAllGroupsWithAmountOfStudentsLessThanAndEqual(inputProcessor.getIntInput());
		SchoolApp.clearConsoleImitation();
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("        >>>1.Find all groups with less or equal students’ number<<<       ");
		System.out.println();
		for(GroupInfo  s: groupsFromSQL) {
			System.out.println("Group with name: " + s.getGroupName() + " has " + s.getAmountOfStudents() + " students;");
		}
		System.out.println();
		System.out.println("For return to main menu put 0(zero)");
	}

}
