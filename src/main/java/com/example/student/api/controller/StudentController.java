package com.example.student.api.controller;

import com.example.student.api.model.StudentUpdateModel;
import com.example.student.domain.model.Student;
import com.example.student.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> listStudents(){
        return studentService.listStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student insertStudent(@Valid @RequestBody Student student){
        return studentService.insertStudent(student);
    }

    @DeleteMapping("/{student_Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long student_Id){
        studentService.deleteStudent(student_Id);
    }

    @PutMapping("/{student_Id}")
    @Transactional
    public Student updateStudent(
            @PathVariable Long student_Id,
            @RequestBody StudentUpdateModel studentUpdateModel
            ){
        return studentService.updateStudent(student_Id, studentUpdateModel);
    }
}
