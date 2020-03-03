package sk.accountigo.courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.accountigo.courses.model.Course;
import sk.accountigo.courses.model.CoursesRepository;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final CoursesRepository repository;

    public CoursesController(CoursesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String name, @PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
