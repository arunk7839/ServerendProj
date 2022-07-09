package com.example.test.sevice;

import com.example.test.model.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getCourses();

    Course getCourse(long parseLong);

    Course addCourse(Course course);

    Course updateCourse(Course course);

    void deleteCourse(long parseLong);

//    User addUser(User user);
//
//    public List<User> getUsers();
}




