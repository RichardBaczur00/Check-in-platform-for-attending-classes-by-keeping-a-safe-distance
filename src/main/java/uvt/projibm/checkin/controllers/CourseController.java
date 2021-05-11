package uvt.projibm.checkin.controllers;

import org.springframework.web.bind.annotation.*;
import uvt.projibm.checkin.exceptions.CourseNotFoundException;
import uvt.projibm.checkin.models.Course;
import uvt.projibm.checkin.models.Filter;
import uvt.projibm.checkin.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    private final CourseRepository repo;

    public CourseController(CourseRepository repo) { this.repo = repo; }

    @GetMapping("/class/all")
    private List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        repo.findAll().forEach(courses::add);

        return courses;
    }

    @GetMapping("/class")
    private List<Course> getCoursesMatching(@RequestBody Filter filter) {
        List<Course> courses = new ArrayList<>();

        repo.findAll().forEach(course -> {
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
    private Course addCourse(@RequestBody Course course) { return repo.save(course); }

    @DeleteMapping("/class/{id}")
    private void deleteUser(@PathVariable Integer id) { repo.deleteById(id); }

    @GetMapping("/class/{id}")
    private Course getCourse(@PathVariable Integer id) {
        return repo.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }
}
