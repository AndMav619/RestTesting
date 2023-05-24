package com.example.demo.student

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class StudentService @Autowired constructor(private val studentRepository: StudentRepository) {
    open val students: List<Student>
        get() = studentRepository.findAll()

    fun addNewStudent(student: Student) {
        val checkedStudent = studentRepository.checkForStudent(student.email)
        check(!checkedStudent.isPresent) { "email already registered" }
        studentRepository.save(student)
    }

    fun deleteStudent(studentId: Long) {
        val exists = studentRepository.existsById(studentId)
        check(exists) { "student with id $studentId does not exist" }
        studentRepository.deleteById(studentId)
    }

    @Transactional
    fun updateStudent(studentId: Long, name: String?, email: String?) {
        val student = studentRepository.findById(studentId)
        check(student.isPresent) { "student with id $studentId does not exist" }
        if (name != null && name != student.get().name) {
            student.get().name = name
        }
        if (email != null && email != student.get().email) {
            val checkedStudent = studentRepository.checkForStudent(email)
            check(!checkedStudent.isPresent) { "email already registered" }
            student.get().email = email
        }
    }
}
