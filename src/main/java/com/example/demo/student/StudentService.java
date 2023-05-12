package com.example.demo.student;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> checkedStudent = studentRepository.checkForStudent(student.getEmail());
        if (checkedStudent.isPresent()){
            throw new IllegalStateException("email already registered");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("student with id "+ studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        boolean exists = studentRepository.existsById(studentId);
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()){
                throw new IllegalStateException("student with id "+ studentId + " does not exist");
        }
        if (name!=null && !name.equals(student.get().getName())){
            student.get().setName(name);
        }
        if (email!=null && !email.equals(student.get().getEmail())){
            Optional<Student> checkedStudent = studentRepository.checkForStudent(email);
            if (checkedStudent.isPresent()){
                throw new IllegalStateException("email already registered");
            }
            student.get().setEmail(email);
        }


    }
}
