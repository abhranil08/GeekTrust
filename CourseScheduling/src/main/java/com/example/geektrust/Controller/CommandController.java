package com.example.geektrust.Controller;

import com.example.geektrust.Repository.CourseRepository;
import com.example.geektrust.Util.Constants;

public class CommandController {

    public Boolean validateInputs( String executionCommand )
    {
        if( executionCommand.split( " ").length != Constants.DEFAULT_COURSE_PARAMETER_SIZE)
        return false;
        return true;
    } 

    void parseInputsAndExecuteCommands( String executionCommand, CourseRepository courseRepository ){}

}
