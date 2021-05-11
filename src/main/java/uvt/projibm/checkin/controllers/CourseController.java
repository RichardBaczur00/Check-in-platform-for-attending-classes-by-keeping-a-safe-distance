package uvt.projibm.checkin.controllers;

import org.springframework.web.bind.annotation.*;
import uvt.projibm.checkin.exceptions.CourseNotFoundException;
import uvt.projibm.checkin.exceptions.UserNotFoundException;
import uvt.projibm.checkin.models.Course;
import uvt.projibm.checkin.models.Enrollment;
import uvt.projibm.checkin.models.Filter;
import uvt.projibm.checkin.models.User;
import uvt.projibm.checkin.repositories.CourseRepository;
import uvt.projibm.checkin.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseController(CourseRepository courseRepository,
                            UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/class/all")
    private List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        courseRepository.findAll().forEach(courses::add);

        return courses;
    }

    @GetMapping("/class")
    private List<Course> getCoursesMatching(@RequestBody Filter filter) {
        List<Course> courses = new ArrayList<>();

        courseRepository.findAll().forEach(course -> {
            if (filter.getField().equals("Name") && filter.getData().equals(course.getName())) {
                courses.add(course);
            } else if (filter.getField().equals("Year") && Integer.parseInt(filter.getData()) == course.getYear()) {
                courses.add(course);
            } else if (filter.getField().equals("Teacher") && Integer.parseInt(filter.getData()) == course.getTeacher().getId()) {
                courses.add(course);
            } else if (filter.getField().equals("Section") && filter.getData().equals(course.getSection())) {
                courses.add(course);
            } else if (filter.getField().equals("Classroom") && Integer.parseInt(filter.getData()) == course.getClassroom().getId()) {
                courses.add(course);
            }
        });

        return courses;
    }

    @PostMapping("/class")
    private Course addCourse(@RequestBody Course course) { return courseRepository.save(course); }

    @DeleteMapping("/class/{id}")
    private void deleteUser(@PathVariable Integer id) { courseRepository.deleteById(id); }

    @GetMapping("/class/{id}")
    private Course getCourse(@PathVariable Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PostMapping("/enrollment")
    private void addEnrollment(@RequestBody Enrollment enrollment) {
        Course joinedCourse = courseRepository.findById(enrollment.getCourseId()).orElseThrow(() ->
                new CourseNotFoundException(enrollment.getCourseId()));
        User newStudent = userRepository.findById(enrollment.getUserId()).orElseThrow(() ->
                new UserNotFoundException(enrollment.getUserId()));

        HashSet<User> oldStudentList = joinedCourse.getStudents();
        oldStudentList.add(newStudent);
        joinedCourse.setStudents(oldStudentList);
    }

    @DeleteMapping("/enrollment")
    private void removeEnrollment(@RequestBody Enrollment enrollment) {
        Course joinedCourse = courseRepository.findById(enrollment.getCourseId()).orElseThrow(() ->
                new CourseNotFoundException(enrollment.getCourseId()));
        User newStudent = userRepository.findById(enrollment.getUserId()).orElseThrow(() ->
                new UserNotFoundException(enrollment.getUserId()));

        HashSet<User> oldStudentList = joinedCourse.getStudents();
        oldStudentList.remove(newStudent);
        joinedCourse.setStudents(oldStudentList);
    }
}
