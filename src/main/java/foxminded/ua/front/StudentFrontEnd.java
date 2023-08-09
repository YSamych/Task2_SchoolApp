package foxminded.ua.front;

import java.util.List;

import org.springframework.stereotype.Service;

import foxminded.ua.app.InputProcessor;
import foxminded.ua.dao.StudentDao;
import foxminded.ua.logic.StudentService;
import foxminded.ua.model.Student;

@Service
public class StudentFrontEnd {
	
	private final InputProcessor inputProcessor;
	private final StudentService studentService;
	public StudentFrontEnd(InputProcessor inputProcessor, StudentService studentService) {
		this.inputProcessor = inputProcessor;
		this.studentService = studentService;
	}
	
	//------------------3.Add a new student----------------------//
	public void runAddNewStudentOption() {
		addStudentOptionView();
		System.out.println("Please, print Students' name, presss \"Enter\", then print Students' last name and presss \"Enter\" again");
		String firstName = inputProcessor.getStringInput();
		String lastName = inputProcessor.getStringInput();
		studentService.addStudent(firstName, lastName);
		if(studentService.studentExists(firstName, lastName)) {
			System.out.println("Student was added");
		}
		System.out.println();
		System.out.println("For return to main menu put 0(zero)");
	}
	
	private void addStudentOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|                        >>>3.Add a new student<<<                       |");
		System.out.println("|                                                                        |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	//---------------------------4.Delete a student by the STUDENT_ID--------------------//
	public void runDeleteStudentByIdOption() {
		deleteStudentByIdOptionView();
		studentService.deleteStudentById(inputProcessor.getLongInput());
	}
	
	private void deleteStudentByIdOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|               >>>4.Delete a student by the STUDENT_ID<<<               |");
		System.out.println("|                                                                        |");
		System.out.println("|Please, enter student_id below                                          |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	//-----------------2.Find all students related to the course with the given name--------------------//
	public void runFindAllStudentsRelatedToCourseOption() {
		findAllStudentsRelatedToCourseOptionView();
		findAllStudentsRelatedToCourseOptionOutput();
	}
	
	private void findAllStudentsRelatedToCourseOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|   >>>2.Find all students related to the course with the given name<<<  |");
		System.out.println("|                                                                        |");
		System.out.println("|Please, enter course name below                                         |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	public void findAllStudentsRelatedToCourseOptionOutput() {
		String courseName = inputProcessor.getStringInput();
		List<Student> listOfStudentsFromSql = studentService.findAllStudentRelatedToCourse(courseName);
		if(listOfStudentsFromSql.isEmpty()) {
			System.out.println("No students assigned to " +  courseName + " course were found.... \n Course with such name might not exist");
		} else {
			System.out.println("Please, find list of students assigned to " + courseName + " course below:");
			for(Student s : listOfStudentsFromSql) {
				String studentId = String.format("Student ID: %d", s.getStudentId());
				String groupId = String.format(" Group ID: %d", s.getGroupId());
				String firstName = String.format(" Name: %s", s.getFirstName());
				String lastName = String.format(" %s", s.getLastName());
				System.out.println(studentId + groupId + firstName + lastName);
			}
		}
		System.out.println();
		System.out.println("For return to main menu put 0(zero)");
		
	}
	
	//---------------------------------5.Add a student to the course (from a list)---------------------------------------//
	public void runAddStudentToCourseOption() {
		addStudentToCourseOptionView();
		System.out.println("Please, print Students' id, presss \"Enter\", then print Course id and presss \"Enter\" again");
		studentService.addStudentToCourse(inputProcessor.getIntInput(), inputProcessor.getIntInput());
	}
	
	private void addStudentToCourseOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|            >>>5.Add a student to the course (from a list)<<<           |");
		System.out.println("|                                                                        |");
		System.out.println("|                +=====================================+                 |");
		System.out.println("|                |Course name              |Course id  |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Math basics              |     1     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Biology basics           |     2     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Psychology               |     3     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Astrophysics             |     4     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Neurobiology             |     5     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Further Mathematics      |     6     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Chemistry                |     7     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |History                  |     8     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |English                  |     9     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Java programming         |     10    |                 |");
		System.out.println("|                +=====================================+                 |");
		System.out.println("|                                                                        |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	//---------------------------------6.Remove the student from one of their courses---------------------------------------//
	public void runRemoveStudentFromCourseOption() {
		removeStudentToCourseOptionView();
		System.out.println("Please, print Students' id, presss \"Enter\", then print Course id and presss \"Enter\" again");
		studentService.removeStudentFromCourse(inputProcessor.getIntInput(), inputProcessor.getIntInput());
	}
	
	private void removeStudentToCourseOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|          >>>6.Remove the student from one of their courses.<<<         |");
		System.out.println("|                                                                        |");
		System.out.println("|                +=====================================+                 |");
		System.out.println("|                |Course name              |Course id  |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Math basics              |     1     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Biology basics           |     2     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Psychology               |     3     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Astrophysics             |     4     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Neurobiology             |     5     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Further Mathematics      |     6     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Chemistry                |     7     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |History                  |     8     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |English                  |     9     |                 |");
		System.out.println("|                |-------------------------+-----------|                 |");
		System.out.println("|                |Java programming         |     10    |                 |");
		System.out.println("|                +=====================================+                 |");
		System.out.println("|                                                                        |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
}
