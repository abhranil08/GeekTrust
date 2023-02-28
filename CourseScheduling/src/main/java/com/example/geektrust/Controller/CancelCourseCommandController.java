package com.example.geektrust.Controller;

import com.example.geektrust.Model.Course;
import com.example.geektrust.Repository.CourseRepository;
import com.example.geektrust.Util.Constants;

public class CancelCourseCommandController extends CommandController {

    public void cancelCourse( String courseRegistrationId, CourseRepository courseRepository )
    {
        // Impl - Get course from course id
        String courseId = courseRepository.getRegistrationIdToCourseIdMap().get(courseRegistrationId);
        Course course = courseRepository.getCourseIdToCourseMap().get(courseId);
        if( course.getIsAlloted() == false )
        {
            // Impl - remove registration from course
            course.removeRegisteredEmployees(courseRegistrationId);
            System.out.println(courseRegistrationId + " " + Constants.CANCEL_ACCEPTED );
        }
        else
            System.out.println(courseRegistrationId + " " + Constants.CANCEL_REJECTED );
    }

    @Override
    public void parseInputsAndExecuteCommands( String input, CourseRepository courseRepository )
    {
        String cmd[] = input.split(" ");
        if( super.validateInputs(input) )
        {
            String courseRegistrationId = cmd[1];
            cancelCourse(courseRegistrationId,courseRepository);                    
        }
        else
        {
            System.out.println(Constants.INPUT_DATA_ERROR);
        }     
    } 
    
}
