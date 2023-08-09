package foxminded.ua.front;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foxminded.ua.app.InputProcessor;
import foxminded.ua.app.SchoolApp;
import foxminded.ua.logic.CourseService;
import foxminded.ua.model.Course;

@Service
public class CourseFrontEnd {
	private final InputProcessor inputProcessor;
	@Autowired
	private final CourseService courseService;
	public CourseFrontEnd(InputProcessor inputProcessor, CourseService courseService) {
		this.inputProcessor = inputProcessor;
		this.courseService = courseService;
	}
	
	//----------------------7.Course info----------------------//
	public void runCourseInfoOption() {
		courseInfoOptionView();
		String courseName = inputProcessor.getStringInput();
		courseInfoOptionOutput(courseName);
	}
	
	private void courseInfoOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|                          >>>7.Course info<<<                           |");
		System.out.println("|                                                                        |");
		System.out.println("|Please, enter course name                                               |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	public void courseInfoOptionOutput(String courseName) {
		SchoolApp.clearConsoleImitation();
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("                           >>>7.Course info<<<                            ");
		System.out.println();
		Optional<Course> courseFromSql = courseService.findByName(courseName);
		if(courseFromSql.isPresent()) {
			Course courseInfo = courseFromSql.get();
			System.out.println("\n\n");
			System.out.println("Course ID: " + courseInfo.getCourseId() + " | Course Name: " + courseInfo.getCourseName() + " | Course Description: " + courseInfo.getCourseDescription());
		} else {
			System.out.println();
			System.out.println("No data was obtained from DB...");
		}
		System.out.println();
		System.out.println("For return to main menu put 0(zero)");
	}
	
	//----------------8.Add Course-------------//
	public void runAddCourseOption() {
		addCourseOptionView();
		addCourseOptionOutput(inputProcessor.getStringInput(), inputProcessor.getStringInput());
	}
	
	private void addCourseOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|                           >>>8.Add Course<<<                           |");
		System.out.println("|                                                                        |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	public void addCourseOptionOutput(String courseName, String courseDescription) {
		SchoolApp.clearConsoleImitation();
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("                            >>>8.Add Course<<<                            ");
		System.out.println();
		courseService.addCourse(courseName, courseDescription);
		if(courseService.findByName(courseName).isPresent()) {
			System.out.println("New course was added...");
		}
		System.out.println();
		System.out.println("For return to main menu put 0(zero)");
	}
	
	//--------------9.Update Course Name-----------//
	public void runUpdateCourseNameOption() {
		updateCourseNameOptionView();
		System.out.println("Enter current name of the Course you want to change");
		String currentName = inputProcessor.getStringInput();
		System.out.println("Enter new name of the Course");
		String newName = inputProcessor.getStringInput();
		updateCourseNameOptionOutput(currentName, newName);
	}
	
	private void updateCourseNameOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|                       >>>9.Update Course Name<<<                       |");
		System.out.println("|                                                                        |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	private void updateCourseNameOptionOutput(String currentName, String newName) {
		SchoolApp.clearConsoleImitation();
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("                        >>>9.Update Course Name<<<                        ");
		System.out.println();
		courseService.updateCourseName(currentName, newName);
		if(courseService.findByName(newName).isPresent()) {
			System.out.println("Course name was changed from " + currentName + " to " + newName);
		}
		System.out.println();
		System.out.println("For return to main menu put 0(zero)");
	}
	
	//--------------10.Update Course Description-----------//
	public void runUpdateCourseDescriptionOption() {
		updateCourseDescriptionOptionView();
		updateCourseDescriptionOptionOutput(inputProcessor.getStringInput(), inputProcessor.getStringInput());
	}

	private void updateCourseDescriptionOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|                  >>>10.Update Course Description<<<                    |");
		System.out.println("|                                                                        |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	public void updateCourseDescriptionOptionOutput(String courseName, String newDescription) {
		SchoolApp.clearConsoleImitation();
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("                   >>>10.Update Course Description<<<                     ");
		System.out.println();
		courseService.updateCourseDescription(courseName, newDescription);
		
		Optional<Course> updatedCourse = courseService.findByName(courseName);
		if(updatedCourse.isPresent() && updatedCourse.get().getCourseDescription().equals(newDescription)) {
			System.out.println("New description was set for course " + courseName);
		}
		System.out.println();
		System.out.println("For return to main menu put 0(zero)");
	}
	
	//------------------11.Delete Course---------------//
	public void runDeleteCourseOption() {
		deleteCourseOptionView();
		deleteCourseOptionOutput(inputProcessor.getStringInput());
	}
	
	private void deleteCourseOptionView() {
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("|0.Back to main menu                                                     |");
		System.out.println("|                         >>>11.Delete Course<<<                         |");
		System.out.println("|                                                                        |");
		System.out.println("<------------------------------------------------------------------------>\n");
	}
	
	public void deleteCourseOptionOutput(String courseName) {
		SchoolApp.clearConsoleImitation();
		System.out.println("<---------------------------School Application--------------------------->");
		System.out.println("                          >>>11.Delete Course<<<                          ");
		System.out.println();
		courseService.deleteCourse(courseName);
		if(!courseService.findByName(courseName).isPresent()) {
			System.out.println("Course with name: " + courseName + " was successfully deleted");
		}
		System.out.println();
		System.out.println("For return to main menu put 0(zero)");
	}

}
