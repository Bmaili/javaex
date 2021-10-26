package dao.impl;

import dao.OptionalCourseDao;
import bean.OptionalCourse;

import java.util.List;

public class OptionalCourseDaoImpl extends BaseDao implements OptionalCourseDao {
    @Override
    public int deleteStuSelectReCourseByCourseID(Integer reCourseID) {
        String sql = "delete from tb_selectCourse where `courseId`=?";
        return update(sql, reCourseID);
    }

    @Override
    public int deleteOpCourseById(Integer id) {
        String sql = "delete from tb_opCourse where `id` = ?";
        return update(sql, id);
    }

    @Override
    public int deleteStuSelectReCourseByStudentID(Integer studentID) {
        String sql = "delete from tb_selectCourse where `studentId` = ?";
        return update(sql, studentID);
    }


    @Override
    public int subStuNumOfReCoursesByStudentID(Integer studentID) {
        String sql = "UPDATE tb_opCourse  SET `stuNum`=`stuNum`-1  WHERE tb_opCourse.id IN (SELECT courseId FROM tb_selectCourse WHERE studentId =?)";
        return update(sql, studentID);
    }

    @Override
    public int updateOpCourseByID(OptionalCourse op) {
        String sql = "update tb_opCourse set `name`=?,`type`=?,`stuNum`=? ,`teacherId`=?,`teacher`=?,`maxStuNum`=? WHERE `id` = ?";
        return update(sql, op.getName(), op.getType(), op.getStuNum(), op.getTeacherId(), op.getTeacher(), op.getMaxStuNum(), op.getId());
    }

    @Override
    public OptionalCourse queryOpCourseByID(Integer courseID) {
        String sql = "select * from tb_opCourse where `id` = ?";
        return queryForOne(OptionalCourse.class, sql, courseID);
    }

    @Override
    public int addOptionalCourse(OptionalCourse op) {
        String sql = "insert into tb_opCourse(`name`,`type`,`stuNum`,`teacherId`,`teacher`,`maxStuNum`)values(?,1,0,?,(SELECT `name` FROM `tb_teacher` WHERE id = ?),?) ";
        return update(sql, op.getName(), op.getTeacherId(), op.getTeacherId(), op.getMaxStuNum());
    }

    @Override
    public List<OptionalCourse> queryAllOpCourses() {
        String sql = "select * from tb_opCourse";
        return queryForList(OptionalCourse.class, sql);
    }

    @Override
    public int studentSelectOpCourse(Integer studentID, Integer opCourseID) {
        String sql1 = "update tb_opCourse set `stuNum`=`stuNum`+1 where `id` = ?";
        String sql2 = "insert into tb_selectCourse(`courseId`,`studentId`) values(?,?)";
        int result1 = update(sql1, opCourseID);
        int result2 = update(sql2, opCourseID, studentID);
        return ((result1 == -1) || (result2 == -1)) ? -1 : result1 + result2;
    }

    @Override
    public List<OptionalCourse> queryOpCoursesByStuID(Integer studentID) {
        String sql = "select tb_opCourse.* from tb_opCourse join tb_selectCourse on tb_opCourse.id=tb_selectCourse.courseId where tb_selectCourse.studentId=?";
        return queryForList(OptionalCourse.class, sql, studentID);
    }


    @Override
    public List<OptionalCourse> queryUnselectOpCoursesByStuID(Integer studentID) {
        String sql = "SELECT tb_opCourse.* FROM tb_opCourse WHERE `id` NOT IN" +
                "(SELECT courseId FROM tb_selectCourse WHERE studentId =?) AND tb_opCourse.`stuNum`<tb_opCourse.`maxStuNum`";
        return queryForList(OptionalCourse.class, sql, studentID);
    }

    @Override
    public List<OptionalCourse> queryOpCoursesByTeaID(Integer teacherID) {
        String sql = "select * from tb_opCourse where `teacherId`=?";
        return queryForList(OptionalCourse.class, sql, teacherID);
    }

    @Override
    public List<OptionalCourse> queryDescSortedOpCoursesByStuNum() {
        String sql = "select * from tb_opCourse order by `stuNum` desc";
        return queryForList(OptionalCourse.class, sql);
    }
}
