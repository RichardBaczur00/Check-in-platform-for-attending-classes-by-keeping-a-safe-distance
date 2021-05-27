package uvt.projibm.checkin.controllers;

import org.springframework.web.bind.annotation.*;
import uvt.projibm.checkin.models.Classroom;
import uvt.projibm.checkin.repositories.ClassroomRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClassroomController {

    private final ClassroomRepository repo;

    public ClassroomController(ClassroomRepository repo) { this.repo = repo; }

    @GetMapping("/classrooms")
    private List<Classroom> getAllClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();

        repo.findAll().forEach(classrooms::add);

        return classrooms;
    }

    @PostMapping("/classroom")
    private Classroom addClassroom(@RequestBody Classroom classroom) {
        return repo.save(classroom);
    }

    @DeleteMapping("/classroom/{id}")
    private void deleteClassroom(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
