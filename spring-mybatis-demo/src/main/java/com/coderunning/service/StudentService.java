package com.coderunning.service;

import com.coderunning.domain.Student;

import java.util.List;

public interface StudentService {

    int addStudent(Student student);

    List<Student> queryStudent();

    String queryStudentByName(String name);
}
