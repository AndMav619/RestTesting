package com.example.demo.student

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import java.util.Optional

@Repository
interface StudentRepository : JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s where s.email=?1")
    fun checkForStudent(email: String): Optional<Student>
}
