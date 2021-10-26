package service;

/**
 * 此接口用于规范针对于学生的服务方法
 */
public interface StudentService {
    /**
     * 查看该学生的所有课程
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int showMyCourses();

    /**
     * 学生选课
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int SelectOpCourse();

    /**
     * 学生修改密码
     *
     * @return 操作成功返回1；操作失败返回-1
     */
    public int changePassword();

}
