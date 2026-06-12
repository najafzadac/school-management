package com.example.school.services;
import com.example.school.entity.Teacher;
import com.example.school.dto.TeacherRequest;
import com.example.school.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    public  ByteArrayOutputStream exportToExcel() throws IOException{
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet= workbook.createSheet("Teachers");
        Row headerRow=sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("name");
        headerRow.createCell(2).setCellValue("email");
        headerRow.createCell(3).setCellValue("subject");

        List<Teacher> teachers= teacherRepository.findAll();
        int rowNum=1;
        for (Teacher teacher : teachers){
            Row row= sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(teacher.getId());
            row.createCell(1).setCellValue(teacher.getFullName());
            row.createCell(2).setCellValue(teacher.getEmail());
            row.createCell(3).setCellValue(teacher.getSubject());
        }
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out;

    }

}
