CREATE TABLE IF NOT EXISTS school_group (
		group_id SERIAL PRIMARY KEY NOT NULL, 
		group_name VARCHAR(5) NOT NULL);
INSERT INTO school_group(group_name) Values('ab-02');
INSERT INTO school_group(group_name) Values('hg-54');
INSERT INTO school_group(group_name) Values('kf-67');



CREATE TABLE IF NOT EXISTS student (
		student_id SERIAL PRIMARY KEY NOT NULL,
		group_id INT,
		first_name VARCHAR(255) NOT NULL,
		last_name VARCHAR(255) NOT NULL);
		
ALTER TABLE student
ADD FOREIGN KEY (group_id) REFERENCES school_group(group_id);

INSERT INTO student(first_name, last_name, group_id) Values('Elijah', 'Jackson', 1);
INSERT INTO student(first_name, last_name, group_id) Values('Theodore', 'Gonzalez', 1);
INSERT INTO student(first_name, last_name, group_id) Values('Sophia', 'Lopez', 2);
INSERT INTO student(first_name, last_name, group_id) Values('William', 'Wilson', 1);
INSERT INTO student(first_name, last_name, group_id) Values('Isabella', 'Lopez', 3);
INSERT INTO student(first_name, last_name, group_id) Values('Isabella', 'Jones', 2);
INSERT INTO student(first_name, last_name, group_id) Values('Liam', 'Brown', 1);
INSERT INTO student(first_name, last_name, group_id) Values('Noah', 'Garcia', 3);
INSERT INTO student(first_name, last_name, group_id) Values('Oliver', 'Miller', 2);
INSERT INTO student(first_name, last_name, group_id) Values('Olivia', 'Davis', 1);
INSERT INTO student(first_name, last_name, group_id) Values('Emma', 'Rodriguez', 1);
INSERT INTO student(first_name, last_name, group_id) Values('James', 'Hernandez', 2);
INSERT INTO student(first_name, last_name, group_id) Values('Amelia', 'Taylor', 3);
INSERT INTO student(first_name, last_name, group_id) Values('Mia', 'Thomas', 1);
INSERT INTO student(first_name, last_name, group_id) Values('Henry', 'Moore', 2);
INSERT INTO student(first_name, last_name, group_id) Values('Lucas', 'Lee', 3);
INSERT INTO student(first_name, last_name, group_id) Values('Benjamin', 'White', 1);
INSERT INTO student(first_name, last_name, group_id) Values('Evelyn', 'Harris', 2);




CREATE TABLE IF NOT EXISTS course (
		course_id SERIAL PRIMARY KEY,
		course_name VARCHAR(255) NOT NULL,
		course_description VARCHAR(255) NOT NULL);
		
INSERT INTO course(course_name, course_description) Values('Math basics', 'Basics of math');
INSERT INTO course(course_name, course_description) Values('Biology basics', 'Basics of biology');
INSERT INTO course(course_name, course_description) Values('Psychology', 'Psychology - the scientific study of the mind and behavior');
INSERT INTO course(course_name, course_description) Values('Astrophysics', 'Branch of space science that applies the laws of physics and chemistry to seek to understand the universe and our place in it');
INSERT INTO course(course_name, course_description) Values('Neurobiology', 'The study of the nervous system and how the brain works');
INSERT INTO course(course_name, course_description) Values('Further Mathematics', 'Mathematical induction, complex number, polar curve and conic sections, differential equations, etc.');
INSERT INTO course(course_name, course_description) Values('Chemistry', 'The study of substances');
INSERT INTO course(course_name, course_description) Values('History', 'The systematic study and documentation of human activity');
INSERT INTO course(course_name, course_description) Values('English', 'English language course');
INSERT INTO course(course_name, course_description) Values('Java programming', 'The best programming language in the world :)');



CREATE TABLE IF NOT EXISTS studentscourses (
		id SERIAL PRIMARY KEY NOT NULL,
		student_id INT NOT NULL,
		course_id INT NOT NULL);
	
ALTER TABLE studentscourses
ADD FOREIGN KEY (course_id) REFERENCES course(course_id);

ALTER TABLE studentscourses
ADD FOREIGN KEY (student_id) REFERENCES student(student_id);


INSERT INTO studentscourses(student_id, course_id) Values('1', '1');
INSERT INTO studentscourses(student_id, course_id) Values('2', '4');
INSERT INTO studentscourses(student_id, course_id) Values('3', '3');
INSERT INTO studentscourses(student_id, course_id) Values('4', '5');
INSERT INTO studentscourses(student_id, course_id) Values('5', '2');
INSERT INTO studentscourses(student_id, course_id) Values('6', '6');
INSERT INTO studentscourses(student_id, course_id) Values('7', '4');
INSERT INTO studentscourses(student_id, course_id) Values('8', '7');
INSERT INTO studentscourses(student_id, course_id) Values('9', '8');
INSERT INTO studentscourses(student_id, course_id) Values('10', '2');
INSERT INTO studentscourses(student_id, course_id) Values('11', '10');
INSERT INTO studentscourses(student_id, course_id) Values('12', '2');
INSERT INTO studentscourses(student_id, course_id) Values('13', '5');
INSERT INTO studentscourses(student_id, course_id) Values('14', '10');
INSERT INTO studentscourses(student_id, course_id) Values('15', '2');
INSERT INTO studentscourses(student_id, course_id) Values('16', '4');
INSERT INTO studentscourses(student_id, course_id) Values('17', '5');
INSERT INTO studentscourses(student_id, course_id) Values('18', '6');
INSERT INTO studentscourses(student_id, course_id) Values('1', '3');
INSERT INTO studentscourses(student_id, course_id) Values('2', '2');
INSERT INTO studentscourses(student_id, course_id) Values('3', '2');
INSERT INTO studentscourses(student_id, course_id) Values('4', '7');
INSERT INTO studentscourses(student_id, course_id) Values('5', '3');
INSERT INTO studentscourses(student_id, course_id) Values('6', '10');
INSERT INTO studentscourses(student_id, course_id) Values('7', '1');
INSERT INTO studentscourses(student_id, course_id) Values('8', '4');
INSERT INTO studentscourses(student_id, course_id) Values('9', '2');
INSERT INTO studentscourses(student_id, course_id) Values('10', '6');
INSERT INTO studentscourses(student_id, course_id) Values('15', '1');
INSERT INTO studentscourses(student_id, course_id) Values('16', '2');
