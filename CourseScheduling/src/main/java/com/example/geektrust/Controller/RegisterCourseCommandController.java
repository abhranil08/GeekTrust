package com.example.geektrust.Controller;

import java.util.TreeMap;

import com.example.geektrust.Model.Course;
import com.example.geektrust.Model.Employee;
import com.example.geektrust.Repository.CourseRepository;
import com.example.geektrust.Util.Constants;

public class RegisterCourseCommandController extends CommandController{
    public String registerEmployeeToCourse( String emailId, String courseId, CourseRepository courseRepository)
    {
        Employee employee = new Employee(emailId);
        TreeMap<String,Course>  CourseIdToCourseMap = courseRepository.getCourseIdToCourseMap();
        Course course = CourseIdToCourseMap.get(courseId);
        String employeeName = employee.getName();
        String courseName = course.getCourseName();
        String courseRegistrationId = "REG-COURSE-" + employeeName + "-" + courseName;

        if( course.getRegisteredEmployeesSize() < course.getMaxEmployees() )
        {
            //Store courseRegId -> courseId map, as we need it while allotment
            course.setRegisteredEmployees(courseRegistrationId, employee);
            courseRepository.setRegistrationIdToCourseIdMap(courseRegistrationId,courseId);
            
            String status = Constants.ACCEPTED;
            System.out.println( courseRegistrationId + " " + status );
        }
        else
        {
            System.out.println( Constants.COURSE_FULL_ERROR );
        }
        return courseRegistrationId;
    }

    @Override
    public void parseInputsAndExecuteCommands( String input, CourseRepository courseRepository )
    {
        String cmd[] = input.split(" ");
        if( validateInputs(input) )
        {
            String emailId = cmd[1];
            String courseId = cmd[2];
            registerEmployeeToCourse(emailId,courseId,courseRepository);
        }
        else
        {
            System.out.println(Constants.INPUT_DATA_ERROR);
        }      
    } 

    @Override
    public Boolean validateInputs( String executionCommand )
    {
        if( executionCommand.split( " ").length != Constants.REGISTER_COURSE_PARAMETER_SIZE)
        return false;
        return true;
    } 
}
