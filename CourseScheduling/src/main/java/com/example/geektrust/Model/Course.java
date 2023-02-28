package com.example.geektrust.Model;

import java.util.TreeMap;

public class Course {

    private String courseId;
    private String courseName;
    private String instructor;
    private String date;
    private int minEmployees;
    private int maxEmployees;

    private boolean isAlloted;
    
    private TreeMap<String,Employee> registeredEmployees ;

    public Course( String courseId, String courseName, String instructor, String date, int minEmployees, int maxEmployees, boolean isAlloted)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minEmployees = minEmployees;
        this.maxEmployees = maxEmployees;
        this.isAlloted = isAlloted;
        this.registeredEmployees = new TreeMap<>();
    }
    
    public String getCourseName()
    {
        return courseName;
    }

    public String getCourseId()
    {
        return courseId;
    }

    public String getInstructor()
    {
        return instructor;
    }

    public String getDate()
    {
        return date;
    }
    public int getMaxEmployees()
    {
        return maxEmployees;
    }
    public int getMinEmployees()
    {
        return minEmployees;
    }

    public Boolean getIsAlloted()
    {
        return isAlloted;
    }
    public void setIsAlloted( Boolean isAlloted )
    {
        this.isAlloted = isAlloted;
    }

    public TreeMap<String,Employee> getRegisteredEmployees( )
    {
        return registeredEmployees;
    }
    public void setRegisteredEmployees( String courseRegistrationId, Employee employee)
    {
        this.registeredEmployees.put(courseRegistrationId, employee);
    }

    public void removeRegisteredEmployees( String courseRegistrationId )
    {
        this.registeredEmployees.remove(courseRegistrationId);    
    }
    public int getRegisteredEmployeesSize( )
    {
        return this.registeredEmployees.size();    
    }
}
