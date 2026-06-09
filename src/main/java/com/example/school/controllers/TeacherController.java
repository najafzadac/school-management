package com.example.school.controllers;
import com.example.school.dto.TeacherRequest;
import com.example.school.entity.Teacher;
import com.example.school.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody TeacherRequest request){
        return ResponseEntity.ok(teacherService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<Teacher>> getAll(){
        return ResponseEntity.ok(teacherService.getAll());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Teacher> getById(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.getById(id));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @RequestBody TeacherRequest request){
        return ResponseEntity.ok(teacherService.update(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content qaytarır
    }
}
