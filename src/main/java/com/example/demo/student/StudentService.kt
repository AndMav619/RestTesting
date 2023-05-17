package com.example.demo.student

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService @Autowired constructor(private val studentRepository: StudentRepository) {
    fun getStudents(): MutableList<Student> {
        return studentRepository.findAll()
    }

    fun addNewStudent(student: Student) {
        val checkedStudent = studentRepository.checkForStudent(student.email)
        if (checkedStudent.isPresent) {
            throw IllegalStateException("email already registered")
        }else {
            studentRepository.save(student)
        }
    }

    fun deleteStudent(studentId: Long) {
        val exists = studentRepository.existsById(studentId)
        if(!exists) {
            throw java.lang.IllegalStateException("student with id $studentId does not exist")
        }else{
            studentRepository.deleteById(studentId)
        }
    }

    @Transactional
    fun updateStudent(studentId: Long, name: String?, email: String?) {
        val student = studentRepository.findById(studentId)
        if (!student.isPresent) {
            throw java.lang.IllegalStateException("student with id $studentId does not exist")
        }else { //if student exists
            if (name != null && name != student.get().name) {
                student.get().name = name
            }
            if (email != null && email != student.get().email) {
                val checkedStudent = studentRepository.checkForStudent(email)
                if (!checkedStudent.isPresent) {
                    throw java.lang.IllegalStateException("email $email already registered")
                }
                student.get().email = email
            }
        }
    }
}
