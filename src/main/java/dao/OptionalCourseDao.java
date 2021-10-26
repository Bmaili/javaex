package dao;

import bean.OptionalCourse;

import java.util.List;


/**
 * 此接口用于规范针对于tb_reCourse(选修课)表的常用操作
 */
public interface OptionalCourseDao {
    /**
     * 根据课程ID删除一门选修课
     *
     * @param id  该课程的ID
     * @return 执行失败返回-1，否则返回影响的行数
     */
    public int deleteOpCourseById(Integer id);

    /**
     * 根据课程ID更新一门选修课
     *
     * @param
     * @return
     */
    public int updateOpCourseByID(OptionalCourse op);

    /**
     * 根据课程ID查询一门选修课
     *
     * @param
     * @return
     */
    public OptionalCourse queryOpCourseByID(Integer courseID);

    /**
     * 添加一门选修课
     *
     * @param
     * @return
     */
    public int addOptionalCourse(OptionalCourse op);

    /**
     * 查询所有的选修课
     *
     * @param
     * @return
     */
    public List<OptionalCourse> queryAllOpCourses();

    /**
     * 学生选课（选修课）
     *
     * @param
     * @return
     */
    public int studentSelectOpCourse(Integer studentID, Integer opCourseID);

    /**
     * 查询某学生所有的选修课
     *
     * @param studentID 该学生的ID
     * @return 包含该学生所有选修课的列表
     */
    public List<OptionalCourse> queryOpCoursesByStuID(Integer studentID);

    /**
     * 查询某学生在选课时可以选择的选修课
     *
     * @param
     * @return
     */
    public List<OptionalCourse> queryUnselectOpCoursesByStuID(Integer studentID);

    /**
     * 根据教师ID查询他的所有授课课程
     *
     * @param
     * @return
     */
    public List<OptionalCourse> queryOpCoursesByTeaID(Integer teacherID);

    /**
     * 根据学生人数降序查询所有选修课
     *
     * @param
     * @return
     */
    public List<OptionalCourse> queryDescSortedOpCoursesByStuNum();

    /**
     * 根据学生ID使他所有选修课的课程人数减一
     *
     * @param
     * @return
     */
    public int subStuNumOfReCoursesByStudentID(Integer studentID);

    /**
     * 根据学生ID删除所有关于他的选课记录
     *
     * @param
     * @return
     */
    public int deleteStuSelectReCourseByStudentID(Integer studentID);

    /**
     * 根据课程ID删除所有关于它的选课记录
     *
     * @param
     * @return
     */
    public int deleteStuSelectReCourseByCourseID(Integer reCourseID);
}
