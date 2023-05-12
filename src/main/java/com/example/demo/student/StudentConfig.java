package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;
import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository repository){
        return args -> {
            Student andreas = new Student("Andreas",
                    "andreas@gmail.com",
                    LocalDate.of(1992,
                            AUGUST, 6));

            Student nick = new Student("Nick",
                    "nick@gmail.com",
                    LocalDate.of(1995,
                            JANUARY, 1));

            repository.saveAll(
                    List.of(andreas,nick)
            );
            repository.findAll();


        };
    }


}
