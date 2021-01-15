package com.example.student.domain.service;

import com.example.student.api.model.StudentUpdateModel;
import com.example.student.api.utils.InputValidation;
import com.example.student.domain.exception.ClientException;
import com.example.student.domain.model.Student;
import com.example.student.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> listStudents(){
        List<Student> students = studentRepository.findAll();
        for(Student student: students){
            student.calculateAge();
        }

        return students;
    }

    public Student insertStudent(Student student){
        if(isEmailTaken(student.getEmail()))
            throw new ClientException("The email is already taken.", 400);

        return save(student);
    }

    public void deleteStudent(Long id){
        Student student = searchUserId(id);
        studentRepository.delete(student);
    }

    public Student updateStudent(Long student_Id, StudentUpdateModel studentUpdateModel){
        Student student = searchUserId(student_Id);
        if(InputValidation.isValidString(studentUpdateModel.getName())
                && !InputValidation.isEqual(student.getName(), studentUpdateModel.getName()))
            student.setName(studentUpdateModel.getName());

        if(InputValidation.isValidString(studentUpdateModel.getEmail()) && !isEmailTaken(studentUpdateModel.getEmail())
                && !InputValidation.isEqual(student.getEmail(), studentUpdateModel.getEmail()))
            student.setEmail(studentUpdateModel.getEmail());

        return save(student);
    }

    private Student searchUserId(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ClientException("Student not found", 404));
    }

    private boolean isEmailTaken(String email){
        Optional<Student> studentOptional = studentRepository.findByEmail(email);
        if(studentOptional.isPresent())
            return true;

        return false;
    }

    private Student save(Student student){
        Student studentResponse = studentRepository.save(student);
        studentResponse.calculateAge();
        return studentResponse;
    }
}
