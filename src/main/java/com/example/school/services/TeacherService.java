package com.example.school.services;
import com.example.school.entity.Teacher;
import com.example.school.dto.TeacherRequest;
import com.example.school.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    public Teacher create(TeacherRequest request){
        Teacher teacher= new Teacher();
        teacher.setFullName(request.getFullName());
        teacher.setEmail(request.getEmail());
        teacher.setSubject(request.getSubject());
        return teacherRepository.save(teacher);
    }
    public List<Teacher> getAll(){
        return teacherRepository.findAll();
    }
    public Teacher getById(Long id){
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("teacher couldn found"+ id));
    }
    public Teacher update(Long id, TeacherRequest request){
        Teacher teacher=getById(id);
        teacher.setFullName(request.getFullName());
        teacher.setEmail(request.getEmail());
        teacher.setSubject(request.getSubject());
        return teacherRepository.save(teacher);
    }
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

}
