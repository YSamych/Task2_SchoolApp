package foxminded.ua.app;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import foxminded.ua.front.CourseFrontEnd;
import foxminded.ua.front.GroupFrontEnd;
import foxminded.ua.front.StudentFrontEnd;
import jakarta.annotation.PostConstruct;

@Component
public class SchoolApp {
	
	
	private final GroupFrontEnd groupFrontEnd;
	private final StudentFrontEnd studentFrontEnd;
	private final CourseFrontEnd courseFrontEnd;
	private final InputProcessor inputProcessor;
	
	public SchoolApp(GroupFrontEnd groupFrontEnd, StudentFrontEnd studentFrontEnd, CourseFrontEnd courseFrontEnd, InputProcessor inputProcessor) {
		this.groupFrontEnd = groupFrontEnd;
		this.studentFrontEnd = studentFrontEnd;
		this.courseFrontEnd = courseFrontEnd;
		this.inputProcessor = inputProcessor;
	}
	
	@PostConstruct
	public void runApp() throws SQLException {
		clearConsoleImitation();
		mainMenu();
		
		boolean exit = false;
		while(!exit) {
			switch(inputProcessor.getIntInput()) {
			case 0:
				clearConsoleImitation();
				mainMenu(); //Main menu view
				break;
			case 1:
				clearConsoleImitation();
				groupFrontEnd.runFindAllGroupsWithNumberOfStudentsOption(); //1.Find all groups with less or equal students’ number
				break;
			case 2:
				clearConsoleImitation();
				studentFrontEnd.runFindAllStudentsRelatedToCourseOption(); //2.Find all students related to the course with the given name
				break;
			case 3:
				clearConsoleImitation();
				studentFrontEnd.runAddNewStudentOption();; //3.Add a new student
				break;
			case 4:
				clearConsoleImitation();
				studentFrontEnd.runDeleteStudentByIdOption(); //4.Delete a student by the STUDENT_ID
				break;
			case 5:
				clearConsoleImitation();
				studentFrontEnd.runAddStudentToCourseOption(); //5.Add a student to the course (from a list)
				break; 
			case 6:
				clearConsoleImitation();
				studentFrontEnd.runRemoveStudentFromCourseOption(); //6.Remove the student from one of their courses.
				break;
			case 7:
				clearConsoleImitation();
				courseFrontEnd.runCourseInfoOption(); //7.Course info 
				break;
			case 8:
				clearConsoleImitation();
				courseFrontEnd.runAddCourseOption(); //8.Add Course
				break;
			case 9:
				clearConsoleImitation();
				courseFrontEnd.runUpdateCourseNameOption(); //9.Update Course Name 
				break;
			case 10:
				clearConsoleImitation();
				courseFrontEnd.runUpdateCourseDescriptionOption(); //10.Update Course Description 
				break;
			case 11:
				clearConsoleImitation();
				courseFrontEnd.runDeleteCourseOption(); //11.Delete Course 
				break;
			case 12:
				exit = true;
				clearConsoleImitation();
				break;
			default:
				System.out.println("Unknown option was chosen...");
				System.out.print("Please, choose option from the list and put ");
				break;
			}
		}
	}
	
	private void mainMenu() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|                            >>>Main Menu<<<                             |");
		System.out.println("|                                                                        |");
		System.out.println("| 1.Find all groups with less or equal students’ number                  |");
		System.out.println("| 2.Find all students related to the course with the given name          |");
		System.out.println("| 3.Add a new student                                                    |");
		System.out.println("| 4.Delete a student by the STUDENT_ID                                   |");
		System.out.println("| 5.Add a student to the course (from a list)                            |");
		System.out.println("| 6.Remove the student from one of their courses                         |");
		System.out.println("| 7.Course info                                                          |");
		System.out.println("| 8.Add Course                                                           |");
		System.out.println("| 9.Update Course Name                                                   |");
		System.out.println("|10.Update Course Description                                            |");
		System.out.println("|11.Delete Course                                                        |");
		System.out.println("|12.Exit                                                                 |");
		System.out.println("|                                                                        |");
		System.out.println("|Please, choose an option by writing its number below in the console     |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	public static void clearConsoleImitation() {
		for(int i = 0; i < 50; i++)
			System.out.println('\n');
	}
	
}
