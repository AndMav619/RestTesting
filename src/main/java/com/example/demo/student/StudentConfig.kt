package com.example.demo.student

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.Month
import java.util.List

@Configuration
open class StudentConfig {
    @Bean
    open fun commandLineRunner(repository: StudentRepository): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            val andreas = Student("Andreas",
                    "andreas@gmail.com",
                    LocalDate.of(1992, Month.AUGUST, 6))
            val nick = Student("Nick",
                    "nick@gmail.com",
                    LocalDate.of(1995, Month.JANUARY, 1))
            repository.saveAll(
                    listOf(andreas, nick)
            )
            repository.findAll()
        }
    }
}
