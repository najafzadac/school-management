package com.example.school.services;
import com.example.school.dto.StudentRequest;
import com.example.school.entity.Student;
import com.example.school.repositories.StudentRepository;
import com.example.school.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor

public class StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    public Student create(StudentRequest request){
        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());
        var teacher=teacherRepository.findById(request.getTeacherId()).orElseThrow(()-> new RuntimeException("teachet couldnt found"));
        student.setTeacher(teacher);
        return studentRepository.save(student);
    }
    public List<Student> getAll(){
        return studentRepository.findAll();
    }
    public Student getById(Long id){
        return studentRepository.findById(id).orElseThrow(()->new RuntimeException("studentbcouldnt found"+id));

    }
    public Student update(Long id, StudentRequest request) {
        Student student = getById(id);
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());

        var teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Müəllim tapılmadı"));
        student.setTeacher(teacher);

        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
