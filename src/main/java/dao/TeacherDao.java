package dao;

import bean.Teacher;

import java.util.List;

/**
 * 此接口用于规范针对于tb_teacher(教师)表的常用操作
 */
public interface TeacherDao {

    /**
     * 添加一名教师
     *
     * @param teacher bean.Teacher的实例
     * @return 执行失败返回-1，否则返回影响的行数
     */
    public int addTeacher(Teacher teacher);

    /**
     * 根据教师ID删除一名教师
     *
     * @param
     * @return
     */
    public int deleteTeacherById(Integer id);

    /**
     * 更新一名教师
     *
     * @param
     * @return
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 根据教师ID查询教师
     *
     * @param
     * @return
     */
    public Teacher queryTeacherById(Integer teacherID);

    /**
     * 教师登录
     *
     * @param
     * @return
     */
    public Teacher teacherLogin(Integer teacherID, String password);

    /**
     * 查询该教师是否是该必修课的授课教师
     *
     * @param
     * @return
     */
    public boolean hasTheReCourse(Integer teacherID, Integer ReCourseID);

    /**
     * 查询该教师是否是该选修课的授课教师
     *
     * @param
     * @return
     */
    public boolean hasTheOpCourse(Integer teacherID, Integer OpCourseID);

    /**
     * 查询所有教师
     *
     * @param
     * @return
     */
    public List<Teacher> queryAllTeachers();

}
