package com.example.test.sevice.impl;

import com.example.test.repo.CourseRepository;
import com.example.test.repo.UserRepository;
import com.example.test.model.Course;
import com.example.test.sevice.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    //List<Course> courseList;

    @Autowired
    CourseRepository courseDao;

    @Autowired
    UserRepository userDao;

    public CourseServiceImpl() {
        /*courseList = new ArrayList<>();
        courseList.add(new Course(1, "Maths", "Mathemetics"));
        courseList.add(new Course(2, "Biology", "Science"));*/
    }

    @Override
    public List<Course> getCourses() {
        //return courseList;
        return courseDao.findAll();
    }


    @Override
    public Course getCourse(long courseId) {

        return courseDao.findById(courseId).get();
        /*Course course = null;
        for (Course c : courseList) {
            if (c.getId() == courseId) {
                course = c;
                break;
            }
        }
        return course;*/
    }

    @Override
    public Course addCourse(Course course) {
        //courseList.add(course);
        courseDao.save(course);
        return course;

    }

    @Override
    public Course updateCourse(Course course) {
       /* courseList.forEach(c -> {
            if (c.getId() == course.getId()) {
                c.setTitle(course.getTitle());
                c.setDescription(course.getDescription());
            }
        });*/
        courseDao.save(course);
        return course;
    }

    @Override
    public void deleteCourse(long courseId) {
        //courseList = this.courseList.stream().filter(e -> e.getId() != courseId).collect(Collectors.toList());
        Course course = courseDao.findById(courseId).get();
        courseDao.delete(course);
    }

//    @Override
//    public User addUser(User user) {
//        userDao.save(user);
//        return user;
//    }
//
//    @Override
//    public List<User> getUsers() {
//        return userDao.findAll();
//    }
}
