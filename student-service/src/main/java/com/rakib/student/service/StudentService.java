package com.rakib.student.service;

import com.rakib.student.domain.ResponseDTO;
import com.rakib.student.domain.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    ResponseDTO getStudent(Long id) throws IOException;
    List<ResponseDTO> getAllStudent();
    Student updateStudent(Student student, Long id) throws Exception;
    boolean deleteStudent(Long Id);
}
