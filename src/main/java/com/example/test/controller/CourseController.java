package com.example.test.controller;

import com.example.test.model.Course;
import com.example.test.sevice.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin("*")
@RestController
public class CourseController {

    @Autowired
    private CourseService myService;

    @GetMapping("/home")
    public String home() {
        return "This is my Homepage";
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> listOfAllCourses = this.myService.getCourses();
        return new ResponseEntity<List<Course>>(listOfAllCourses, HttpStatus.OK);
        //return this.myService.getCourses();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable String courseId) {
        return this.myService.getCourse(Long.parseLong(courseId));
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return this.myService.addCourse(course);
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course) {
        return this.myService.updateCourse(course);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
        try {
            this.myService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
//
//    @PostMapping("/users")
//    public User addUser(@RequestBody User user) {
//        return this.myService.addUser(user);
//    }
//
//    @GetMapping("/users")
//    public List<User> getUsers()
//    {
//        return this.myService.getUsers();
//    }
}
