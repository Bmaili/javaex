package dao.impl;

import dao.StudentDao;
import bean.Student;

import java.util.List;

public class StudentDaoImpl extends BaseDao implements StudentDao {
    @Override
    public int addStudent(Student student) {
        String sql = "insert into tb_student(`name`,`password`,`stuClass`)values(?,?,?)";
        return update(sql, student.getName(), student.getPassword(), student.getStuClass());
    }

    @Override
    public int deleteStudentById(Integer id) {
        String sql = "delete from tb_student where `id` = ?";
        return update(sql, id);
    }

    @Override
    public Student studentLogin(Integer studentID, String password) {
        String sql = "select * from tb_student where `id`=? and `password`=?";
        return queryForOne(Student.class, sql, studentID, password);
    }

    @Override
    public int updateStudent(Student student) {
        String sql = "update tb_student set `name`=?,`password`=?,`stuClass`=? WHERE `id` = ?";
        return update(sql, student.getName(), student.getPassword(), student.getStuClass(), student.getId());
    }

    @Override
    public List<Student> queryStudentsByOpCourseId(Integer opCourseID) {
        String sql = "SELECT tb_student.* FROM tb_student JOIN tb_selectCourse ON tb_student.`id`=tb_selectCourse.`studentId` WHERE tb_selectCourse.`courseId`=?";
        return queryForList(Student.class, sql, opCourseID);
    }

    @Override
    public List<Student> queryAllStudents() {
        String sql = "select * from tb_student";
        return queryForList(Student.class, sql);
    }

    @Override
    public Student queryStudentById(Integer id) {
        String sql = "select * from tb_student where `id`=?";
        return queryForOne(Student.class, sql, id);
    }
}
