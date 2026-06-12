package com.example.school.services;
import com.example.school.dto.StudentRequest;
import com.example.school.entity.Student;
import com.example.school.repositories.StudentRepository;
import com.example.school.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
                .orElseThrow(() -> new RuntimeException("teacher couldnt found"));
        student.setTeacher(teacher);

        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
    public ByteArrayOutputStream exportToExcel() throws IOException{
        Workbook workbook= new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("students");
        Row headerrow= sheet.createRow(0);
        headerrow.createCell(0).setCellValue("id");
        headerrow.createCell(1).setCellValue("full name");
        headerrow.createCell(2).setCellValue("email");
        headerrow.createCell(3).setCellValue("age");
        headerrow.createCell(4).setCellValue("teacher");

        List<Student> students=studentRepository.findAll();
        int rowNum=1;
        for (Student student : students){
            Row row=sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getFullName());
            row.createCell(2).setCellValue(student.getEmail());
            row.createCell(3).setCellValue(student.getAge());

        if (student.getTeacher()!= null){
            row.createCell(4).setCellValue(student.getTeacher().getFullName());

        }else{
            row.createCell(4).setCellValue("-");
        }

        }
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out;
    }
}
