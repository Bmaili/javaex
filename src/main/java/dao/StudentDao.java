package dao;

import bean.Student;

import java.util.List;

/**
 * 此接口用于规范针对于tb_student(学生)表的常用操作
 */
public interface StudentDao {
    /**
     * 添加一名学生
     *
     * @param student bean.Student的实例
     * @return 执行失败返回-1，否则返回影响的行数
     */
    public int addStudent(Student student);

    /**
     * 根据学生ID删除一名学生
     *
     * @param
     * @return
     */
    public int deleteStudentById(Integer id);

    /**
     * 学生登录
     *
     * @param
     * @return
     */
    public Student studentLogin(Integer studentID, String password);

    /**
     * 更新一名学生
     *
     * @param
     * @return
     */
    public int updateStudent(Student student);

    /**
     * 根据课程ID查询所有选了该课的学生
     *
     * @param
     * @return
     */
    public List<Student> queryStudentsByOpCourseId(Integer opCourseId);

    /**
     * 根据学生ID查询学生
     *
     * @param
     * @return
     */
    public Student queryStudentById(Integer id);

    /**
     * 查询所有学生
     *
     * @param
     * @return
     */
    public List<Student> queryAllStudents();


}
