package com.example.geektrust.Controller;

import java.util.Map;

import com.example.geektrust.Model.Course;
import com.example.geektrust.Model.Employee;
import com.example.geektrust.Repository.CourseRepository;
import com.example.geektrust.Util.Constants;

public class AllotCourseCommandController extends CommandController {
    public void allotCourse( String courseId, CourseRepository courseRepository )
    {
        Course course = courseRepository.getCourseFromCourseId( courseId );
        String status = Constants.CONFIRMED;
        if( course.getRegisteredEmployees().size() < course.getMinEmployees() )
            status = Constants.COURSE_CANCELED;

        for( Map.Entry<String,Employee> entry : course.getRegisteredEmployees().entrySet())
        {
            String emailId = entry.getValue().getEmailId();
            String courseName = course.getCourseName();
            String instructor = course.getInstructor();
            String date = course.getDate();
            String registrationId = entry.getKey();

            course.setIsAlloted( true );
            System.out.println( registrationId + " " + emailId + " " + courseId + " " + courseName + " " + instructor + " " + date + " " + status);  
        }
    }

    @Override
    public void parseInputsAndExecuteCommands( String input, CourseRepository courseRepository  )
    {
        String cmd[] = input.split(" ");
        if( super.validateInputs(input) )
        {
            String courseId = cmd[1];
            allotCourse(courseId,courseRepository);
        }
        else
        {
            System.out.println(Constants.INPUT_DATA_ERROR);
        }          
    } 
    
}
