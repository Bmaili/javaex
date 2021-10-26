package service;

/**
 * 此接口用于规范针对于教师的服务方法
 */
public interface TeacherService {

    /**
     * 查看该教师的所有授课
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int showMyCourses();

    /**
     * 查看所授的某一门课的所有学生
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int showOneCourseStudents();

    /**
     * 教师修改密码
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int changePassword();

}
