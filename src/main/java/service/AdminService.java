package service;

/**
 * 此接口用于规范针对于管理员的服务方法
 */
public interface AdminService {
    /**
     * 查看所有的课程
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int showAllCoursesList();

    /**
     * 查看所有的学生
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int showStudentsList();

    /**
     * 查看所有的教师
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int showTeachersList();

    /**
     * 添加课程
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int addCourse();

    /**
     * 删除课程
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int deleteCourse();

    /**
     * 添加教师
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int addTeacher();

    /**
     * 删除教师
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int deleteTeacher();

    /**
     * 添加学生
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int addStudent();

    /**
     * 删除学生
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int deleteStudent();

    /**
     * 查看按选课人数降序后的课程列表
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int descSortOptionalCourseByStuNum();

    /**
     * 修改一门课程的授课教师
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int changeCouTeacher();

    /**
     * 重置用户密码
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int resetUserPassword();
}

