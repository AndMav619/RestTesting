package com.example.demo.student

import jakarta.persistence.*
import java.time.LocalDate
import java.time.Period

@Entity
@Table
class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    var id: Long? = null
    var name: String? = null
    var email: String? = null
    var dob: LocalDate? = null
    @Transient
    private var age: Int? = null

    constructor()
    constructor(id: Long,
                name: String,
                email: String,
                dob: LocalDate) {
        this.id = id
        this.name = name
        this.email = email
        this.dob = dob
    }

    constructor(name: String,
                email: String,
                dob: LocalDate) {
        this.name = name
        this.email = email
        this.dob = dob
    }

    fun getAge(): Int {
        return Period.between(dob, LocalDate.now()).years
    }

    fun setAge(age: Int) {
        this.age = age
    }

    override fun toString(): String {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}'
    }
}
