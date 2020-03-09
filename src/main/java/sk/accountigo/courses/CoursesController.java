package sk.accountigo.courses;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sk.accountigo.courses.model.Course;
import sk.accountigo.courses.model.CoursesRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        Optional<Course> course = repository.findById(id);
        return ResponseEntity.ok().body(course.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> putCourse(@PathVariable Long id, @RequestBody Course course) {
        Course course1 = repository.findById(id).get();
        course1.setName(course.getName());
        course1.setDescription(course.getDescription());
        repository.save(course1);

        return ResponseEntity.ok(course1);
    }

    @PostMapping("/")
    public ResponseEntity<Course> postCourse(@RequestBody Course course) {
        Course newCourse = new Course(course.getName(), course.getDescription());
        repository.save(newCourse);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCourse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
