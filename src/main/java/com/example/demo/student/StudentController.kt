package com.example.demo.student

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api/v1/student"])
data class StudentController @Autowired constructor(private val studentService: StudentService) {
    @get:GetMapping
    val students: List<Student>
        get() = studentService.students

    @PostMapping
    fun registerNewStudent(@RequestBody student: Student?) {
        studentService.addNewStudent(student)
    }

    @DeleteMapping(path = ["{studentId}"])
    fun deleteStudent(@PathVariable("studentId") studentId: Long?) {
        studentService.deleteStudent(studentId)
    }

    @PutMapping(path = ["{studentId}"])
    fun updateStudent(
            @PathVariable("studentId") studentId: Long?,
            @RequestParam(required = false) name: String?,
            @RequestParam(required = false) email: String?) {
        studentService.updateStudent(studentId, name, email)
    }
}
