package com.example.geektrust.Repository;

import java.util.HashMap;
import java.util.TreeMap;

import com.example.geektrust.Model.Course;

public class CourseRepository {
    private TreeMap<String,Course> courseIdToCourseMap = new TreeMap<>();
    private HashMap<String,String> registrationIdToCourseIdMap = new HashMap<>();
    
    public TreeMap<String,Course> getCourseIdToCourseMap()
    {
        return courseIdToCourseMap;
    }
    public void setCourseIdToCourseMap(String courseId,Course course)
    {
        this.courseIdToCourseMap.put(courseId, course);
    }
    public HashMap<String,String> getRegistrationIdToCourseIdMap()
    {
        return registrationIdToCourseIdMap;
    }
    public void setRegistrationIdToCourseIdMap(String courseRegistrationId,String courseId)
    {
        this.registrationIdToCourseIdMap.put(courseRegistrationId, courseId);
    }
    public Course getCourseFromCourseId(String courseId)
    {
        return this.courseIdToCourseMap.get(courseId);
    }
}
