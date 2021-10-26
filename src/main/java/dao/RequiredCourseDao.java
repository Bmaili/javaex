package dao;


import bean.RequiredCourse;

import java.util.List;

/**
 * 此接口用于规范针对于tb_reCourse(必修课)表的常用操作
 */
public interface RequiredCourseDao {
    /**
     * 根据课程ID删除一门必修课
     *
     * @param id 该课程的ID
     * @return 执行失败返回-1，否则返回影响的行数
     */
    public int deleteReCourseById(Integer id);

    /**
     * 根据课程ID更新一门必修课
     *
     * @param
     * @return
     */
    public int updateReCourseByID(RequiredCourse re);

    /**
     * 根据课程ID查询一门必修课
     *
     * @param
     * @return
     */
    public RequiredCourse queryReCourseByID(Integer courseID);

    /**
     * 添加一门必修课
     *
     * @param
     * @return
     */
    public int addRequiredCourse(RequiredCourse re);

    /**
     * 查询所有的必修课
     *
     * @param
     * @return
     */
    public List<RequiredCourse> queryAllReCourses();

    /**
     * 根据教师ID查询他的所有授课课程
     *
     * @param teacherID 教师ID
     * @return 包含该教师所有所授必修课的列表
     */
    public List<RequiredCourse> queryReCoursesByTeaID(Integer teacherID);

    /**
     * 所有的必修课的选课人数加一
     *
     * @param
     * @return
     */
    public int incStuNumOfAllReCourses();

    /**
     * 所有的必修课的选课人数减一
     *
     * @param
     * @return
     */
    public int subStuNumOfAllReCourses();
}
