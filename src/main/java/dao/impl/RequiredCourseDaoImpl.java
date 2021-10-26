package dao.impl;

import dao.RequiredCourseDao;
import bean.RequiredCourse;

import java.util.List;

public class RequiredCourseDaoImpl extends BaseDao implements RequiredCourseDao {
    @Override
    public int deleteReCourseById(Integer id) {
        String sql = "delete from tb_reCourse where `id` = ?";
        return update(sql, id);
    }

    @Override
    public int updateReCourseByID(RequiredCourse re) {
        String sql = "update tb_reCourse set `name`=?,`type`=?,`stuNum`=? ,`teacherId`=?,`teacher`=?,`credit`=? WHERE `id` = ?";
        return update(sql, re.getName(), re.getType(), re.getStuNum(), re.getTeacherId(), re.getTeacher(), re.getCredit(), re.getId());
    }

    @Override
    public RequiredCourse queryReCourseByID(Integer courseID) {
        String sql = "select * from tb_reCourse where `id` = ?";
        return queryForOne(RequiredCourse.class, sql, courseID);
    }

    @Override
    public int addRequiredCourse(RequiredCourse re) {
        String sql = "insert into tb_reCourse(`name`,`type`,`stuNum`,`teacherId`,`teacher`,`credit`)values(?,0,(SELECT COUNT(*) FROM `tb_student`),?,(SELECT `name` FROM `tb_teacher` WHERE id = ?),?)";
        return update(sql, re.getName(), re.getTeacherId(), re.getTeacherId(), re.getCredit());
    }

    @Override
    public List<RequiredCourse> queryAllReCourses() {
        String sql = "select * from tb_reCourse";
        return queryForList(RequiredCourse.class, sql);
    }

    @Override
    public List<RequiredCourse> queryReCoursesByTeaID(Integer teacherID) {
        String sql = "select * from tb_reCourse where `teacherId`=?";
        return queryForList(RequiredCourse.class, sql, teacherID);
    }

    @Override
    public int incStuNumOfAllReCourses() {
        String sql = "update tb_reCourse set `stuNum`=`stuNum`+1";
        return update(sql);
    }

    @Override
    public int subStuNumOfAllReCourses() {
        String sql = "update tb_reCourse set `stuNum`=`stuNum`-1";
        return update(sql);
    }
}
