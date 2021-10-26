package dao.impl;

import dao.TeacherDao;
import bean.OptionalCourse;
import bean.RequiredCourse;
import bean.Teacher;

import java.util.List;

public class TeacherDaoImpl extends BaseDao implements TeacherDao {
    @Override
    public Teacher queryTeacherById(Integer teacherID) {
        String sql = "select * from tb_teacher where `id`=?";
        return queryForOne(Teacher.class, sql, teacherID);
    }

    @Override
    public Teacher teacherLogin(Integer teacherID, String password) {
        String sql = "select * from tb_teacher where `id`=? and `password`=?";
        return queryForOne(Teacher.class, sql, teacherID, password);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        String sql = "update tb_teacher set `name`=?,`password`=?,`level`=? WHERE `id` = ?";
        return update(sql, teacher.getName(), teacher.getPassword(), teacher.getLevel(), teacher.getId());
    }

    @Override
    public boolean hasTheReCourse(Integer teacherID, Integer ReCourseID) {
        String sql = "select * from tb_reCourse where `teacherId`=? and `id`=?";
        RequiredCourse re = queryForOne(RequiredCourse.class, sql, teacherID, ReCourseID);
        return re != null;
    }

    @Override
    public boolean hasTheOpCourse(Integer teacherID, Integer OpCourseID) {
        String sql = "select * from tb_opCourse where `teacherId`=? and `id`=?";
        OptionalCourse op = queryForOne(OptionalCourse.class, sql, teacherID, OpCourseID);
        return op != null;
    }

    @Override
    public List<Teacher> queryAllTeachers() {
        String sql = "select * from tb_teacher";
        return queryForList(Teacher.class, sql);
    }

    public int addTeacher(Teacher teacher) {
        String sql = "insert into tb_teacher(`name`,`password`,`level`)values(?,?,?)";
        return update(sql, teacher.getName(), teacher.getPassword(), teacher.getLevel());
    }

    @Override
    public int deleteTeacherById(Integer id) {
        String sql = "delete from tb_teacher where `id` = ?";
        return update(sql, id);
    }
}
