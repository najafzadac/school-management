package com.example.school.dto;

import lombok.Data;
@Data

public class StudentRequest {
    private String fullName;
    private String email;
    private Integer age;
    private Long teacherId;
}
