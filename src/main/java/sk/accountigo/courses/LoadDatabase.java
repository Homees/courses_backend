package sk.accountigo.courses;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sk.accountigo.courses.model.Course;
import sk.accountigo.courses.model.CoursesRepository;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner init(CoursesRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Course("course1",
                    "Learn Full stack with Spring Boot and Angular")));
            log.info("Preloading " + repository.save(new Course("course2",
                    "Learn Full stack with Spring Boot and React")));
            log.info("Preloading " + repository.save(new Course("course3",
                    "Master Microservices with Spring Boot and Spring Cloud")));
            log.info("Preloading " + repository.save(new Course("course4",
                    "Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes")));
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/instructors/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }
}
