package com.coderunning.service.impl;

import com.coderunning.dao.StudentDao;
import com.coderunning.domain.Student;
import com.coderunning.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public List<Student> queryStudent() {

        return studentDao.selectStudents();
    }
}
