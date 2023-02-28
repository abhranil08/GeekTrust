package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.example.geektrust.Controller.AddCourseCommandController;
import com.example.geektrust.Controller.AllotCourseCommandController;
import com.example.geektrust.Controller.CancelCourseCommandController;
import com.example.geektrust.Controller.CommandController;
import com.example.geektrust.Controller.RegisterCourseCommandController;
import com.example.geektrust.Repository.CourseRepository;
import com.example.geektrust.Util.Constants;

public class Main { 
    static AddCourseCommandController addCourseCommandController = new AddCourseCommandController();
    static RegisterCourseCommandController registerCourseCommandController = new RegisterCourseCommandController();
    static CancelCourseCommandController cancelCommandController = new CancelCourseCommandController();
    static AllotCourseCommandController allotCourseCommandController = new AllotCourseCommandController();
    static CourseRepository courseRepository = new CourseRepository();
    static CommandController commandController = new CommandController();
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); 
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                Execute( input, commandController, addCourseCommandController, registerCourseCommandController,
                        cancelCommandController, allotCourseCommandController, courseRepository);           
            
            }
            sc.close(); // closes the scanner
        } catch ( IOException e ) {} 
    }

    private static void Execute( String input, CommandController commandController, AddCourseCommandController addCourseCommandController, 
                                RegisterCourseCommandController registerCourseCommandController, CancelCourseCommandController cancelCommandController, 
                                AllotCourseCommandController allotCourseCommandController, CourseRepository courseRepository  )
    {
    try{
        switch( input.split(" ")[0])
        {
        case "ADD-COURSE-OFFERING":
            addCourseCommandController.parseInputsAndExecuteCommands(input, courseRepository);          
            break;
        case "REGISTER":  
            registerCourseCommandController.parseInputsAndExecuteCommands(input, courseRepository);          
            break;              
        case "ALLOT":
            allotCourseCommandController.parseInputsAndExecuteCommands(input, courseRepository);          
            break;
        case "CANCEL":
            cancelCommandController.parseInputsAndExecuteCommands(input, courseRepository);          
            break;       
    }
    }catch( Exception e )
    {
        System.out.println(Constants.INPUT_DATA_ERROR);
    }
    
    }
}
