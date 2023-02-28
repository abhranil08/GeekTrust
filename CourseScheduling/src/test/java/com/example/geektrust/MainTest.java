package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.geektrust.Controller.AddCourseCommandController;
import com.example.geektrust.Controller.AllotCourseCommandController;
import com.example.geektrust.Controller.CancelCourseCommandController;
import com.example.geektrust.Controller.RegisterCourseCommandController;
import com.example.geektrust.Model.Course;
import com.example.geektrust.Model.Employee;
import com.example.geektrust.Repository.CourseRepository;
import com.example.geektrust.Util.Constants;

public class MainTest {
    AddCourseCommandController addCourseCommandExecutor;
    RegisterCourseCommandController registerCourseCommandExecutor;
    CancelCourseCommandController cancelCourseCommandExecutor;
    AllotCourseCommandController allotCourseCommandExecutor;
    CourseRepository courseRepository;
    
    @BeforeEach
    public void init()
    {
        addCourseCommandExecutor = new AddCourseCommandController();
        registerCourseCommandExecutor = new RegisterCourseCommandController();
        cancelCourseCommandExecutor = new CancelCourseCommandController();
        allotCourseCommandExecutor = new AllotCourseCommandController();
        courseRepository = new CourseRepository();
    }

    @Test
    public void TestNewCourseAndNewRegistration()
    {   
        //Adding course
        String input = "ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2";
        String cmd[] = input.split(" ");      

        String courseName = cmd[1];
        String instructor = cmd[2];
        String Date = cmd[3];
        System.out.println(courseName);
        int minEmployees = Integer.parseInt(cmd[4]);
        int maxEmployees = Integer.parseInt(cmd[5]);
        String courseId = addCourseCommandExecutor.createCourse(courseName,instructor,Date,minEmployees,maxEmployees,courseRepository);
        
        //First registration
        String firstRegistration = "REGISTER ANDY@GMAIL.COM OFFERING-JAVA-JAMES";
        cmd = firstRegistration.split(" ");      
        String emailId = cmd[1];
        courseId = cmd[2];
        String courseRegistrationIdForAndy = registerCourseCommandExecutor.registerEmployeeToCourse(emailId,courseId,courseRepository);

        //Second registration
        String secondRegistration = "REGISTER WOO@GMAIL.COM OFFERING-JAVA-JAMES";
        cmd = secondRegistration.split(" ");      
        emailId = cmd[1];
        courseId = cmd[2];
        String courseRegistrationIdForWoo = registerCourseCommandExecutor.registerEmployeeToCourse(emailId,courseId,courseRepository);


        //Fetching course after new course addition
        Course course = courseRepository.getCourseIdToCourseMap().get(courseId);

        //Fetching first registration details
        Employee employeeAndy = course.getRegisteredEmployees().get(courseRegistrationIdForAndy);
        
        //Fetching second registration details
        Employee employeeWoo = course.getRegisteredEmployees().get(courseRegistrationIdForWoo);
                    
        assertEquals("OFFERING-JAVA-JAMES", courseId );

        assertEquals("ANDY@GMAIL.COM", employeeAndy.getEmailId());
        assertEquals("ANDY", employeeAndy.getName());

        assertEquals("WOO@GMAIL.COM", employeeWoo.getEmailId());
        assertEquals("WOO", employeeWoo.getName());

        assertEquals( course.getRegisteredEmployeesSize() , 2);
    }

    @Test
    public void TestE2EWithOutCancel()
    {
        String AddCourseInput = "ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 3";
        String RegisterCourse1 = "REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB";
        String RegisterCourse2 = "REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB";
        String AllotCourse = "ALLOT OFFERING-DATASCIENCE-BOB";
        
        //Adding Course
        addCourseCommandExecutor.parseInputsAndExecuteCommands(AddCourseInput, courseRepository);

        //Registration
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse1, courseRepository);
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse2, courseRepository);                      
        
        //Alloting course
        allotCourseCommandExecutor.parseInputsAndExecuteCommands(AllotCourse, courseRepository);          
    }

    @Test
    public void TestE2EWithCancel()
    {
        String AddCourseInput = "ADD-COURSE-OFFERING PYTHON JOHN 05062022 1 3";
        String RegisterCourse1 = "REGISTER WOO@GMAIL.COM OFFERING-PYTHON-JOHN";
        String RegisterCourse2 = "REGISTER ANDY@GMAIL.COM OFFERING-PYTHON-JOHN";
        String RegisterCourse3 = "REGISTER BOBY@GMAIL.COM OFFERING-PYTHON-JOHN";
        String CancelCourse = "CANCEL REG-COURSE-BOBY-PYTHON";
        String AllotCourse = "ALLOT OFFERING-PYTHON-JOHN";
        
        //Adding Course
        addCourseCommandExecutor.parseInputsAndExecuteCommands(AddCourseInput, courseRepository);

        //Employee Registration
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse1, courseRepository);
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse2, courseRepository);                      
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse3, courseRepository);                      
        
        //Cancel course
        cancelCourseCommandExecutor.parseInputsAndExecuteCommands(CancelCourse, courseRepository );                      
        
        //Alloting course
        allotCourseCommandExecutor.parseInputsAndExecuteCommands(AllotCourse, courseRepository);          
    }

    @Test
    public void TestRegisteredEmployeesMoreThanMaxLimit()
    {
        String AddCourseInput = "ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 2";
        String RegisterCourse1 = "REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB";
        String RegisterCourse2 = "REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB";
        String RegisterCourse3 = "REGISTER BOBY@GMAIL.COM OFFERING-DATASCIENCE-BOB";
        String AllotCourse = "ALLOT OFFERING-DATASCIENCE-BOB";
        
        //Adding Course
        addCourseCommandExecutor.parseInputsAndExecuteCommands(AddCourseInput, courseRepository);

        //Employee Registration
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse1, courseRepository);
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse2, courseRepository);                      
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse3, courseRepository);                      
        
        //Alloting course
        allotCourseCommandExecutor.parseInputsAndExecuteCommands(AllotCourse, courseRepository);          
    }

    @Test
    public void TestRegisteredEmployeesLessThanMinLimit()
    {
        String AddCourseInput = "ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 3 5";
        String RegisterCourse1 = "REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB";
        String RegisterCourse2 = "REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB";
        String RegisterCourse3 = "REGISTER ALICE@GMAIL.COM";
        String AllotCourse = "ALLOT OFFERING-DATASCIENCE-BOB";
        
        //Adding Course
        addCourseCommandExecutor.parseInputsAndExecuteCommands(AddCourseInput, courseRepository);

        //Employee registration
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse1, courseRepository);
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse2, courseRepository);                      
        registerCourseCommandExecutor.parseInputsAndExecuteCommands(RegisterCourse3, courseRepository);                      
        
        //Allocating course
        allotCourseCommandExecutor.parseInputsAndExecuteCommands(AllotCourse, courseRepository);          
    }

    public void TestConstants()
    {
        String CANCEL_REJECTED = "CANCEL_REJECTED";
        String CANCEL_ACCEPTED = "CANCEL_ACCEPTED";
        String ACCEPTED = "ACCEPTED";
        String CONFIRMED = "CONFIRMED";
        String INPUT_DATA_ERROR = "INPUT_DATA_ERROR";
        String COURSE_FULL_ERROR = "COURSE_FULL_ERROR";
        int ADD_COURSE_PARAMETER_SIZE = 6;
        int REGISTER_COURSE_PARAMETER_SIZE = 3;
        int DEFAULT_COURSE_PARAMETER_SIZE = 2;
        
        assertEquals(Constants.CANCEL_ACCEPTED, CANCEL_ACCEPTED);
        assertEquals(Constants.CANCEL_REJECTED, CANCEL_REJECTED);
        assertEquals(Constants.ACCEPTED, ACCEPTED);
        assertEquals(Constants.CONFIRMED, CONFIRMED);
        assertEquals(Constants.INPUT_DATA_ERROR, INPUT_DATA_ERROR);
        assertEquals(Constants.COURSE_FULL_ERROR, COURSE_FULL_ERROR);
        assertEquals(Constants.ADD_COURSE_PARAMETER_SIZE, ADD_COURSE_PARAMETER_SIZE);
        assertEquals(Constants.REGISTER_COURSE_PARAMETER_SIZE, REGISTER_COURSE_PARAMETER_SIZE);
        assertEquals(Constants.DEFAULT_COURSE_PARAMETER_SIZE, DEFAULT_COURSE_PARAMETER_SIZE);
    }
}