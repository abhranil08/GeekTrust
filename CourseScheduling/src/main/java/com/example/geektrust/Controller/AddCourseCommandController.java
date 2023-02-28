package com.example.geektrust.Controller;

import com.example.geektrust.Model.Course;
import com.example.geektrust.Repository.CourseRepository;
import com.example.geektrust.Util.Constants;

public class AddCourseCommandController extends CommandController {

    public String createCourse( String courseName, String instructor, String date, int minEmployees, int maxEmployees, CourseRepository courseRepository )
    {
        String courseId  = "OFFERING-" + courseName + "-" + instructor;
        Course course = new Course( courseId, courseName, instructor, date, minEmployees, maxEmployees, false );
        courseRepository.setCourseIdToCourseMap(courseId, course);
        return courseId;
    }
    
    @Override
    public void parseInputsAndExecuteCommands( String input, CourseRepository courseRepository  )
    {
        String cmd[] = input.split(" ");
        if( validateInputs(input) )
        {
            String courseName = cmd[1];
            String instructor = cmd[2];
            String Date = cmd[3];
            int minEmployees = Integer.parseInt(cmd[4]);
            int maxEmployees = Integer.parseInt(cmd[5]);
            String courseId = createCourse(courseName,instructor,Date,minEmployees,maxEmployees,courseRepository);
            System.out.println(courseId);
        }
        else
        {
            System.out.println(Constants.INPUT_DATA_ERROR);
        }     
    } 

    @Override
    public Boolean validateInputs( String executionCommand )
    {
        if( executionCommand.split( " ").length != Constants.ADD_COURSE_PARAMETER_SIZE)
        return false; 
        return true;
    } 
}
