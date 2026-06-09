package com.example.school;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String fullName;
    private String email;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnoreProperties("students")
    private Teacher teacher;
    }

