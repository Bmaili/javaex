package service.impl;

import dao.OptionalCourseDao;
import dao.RequiredCourseDao;
import dao.StudentDao;
import dao.impl.OptionalCourseDaoImpl;
import dao.impl.RequiredCourseDaoImpl;
import dao.impl.StudentDaoImpl;
import bean.OptionalCourse;
import bean.RequiredCourse;
import bean.Student;
import service.StudentService;

import java.util.List;
import java.util.Scanner;

/**
 * 提供学生服务的实现类
 */
public class StudentServiceImpl implements StudentService {
    static StudentDao studentDao = new StudentDaoImpl();
    static OptionalCourseDao opCourseDao = new OptionalCourseDaoImpl();
    static RequiredCourseDao reCourseDao = new RequiredCourseDaoImpl();


    private Student student;

    public StudentServiceImpl(Student student) {
        this.student = student;
    }


    @Override
    public int showMyCourses() {   //依次展示选修课、必修课
        System.out.println("选修课：");
        System.out.println("编号ID    课程    类型    已选课人数    教师ID    教师名称    最大选课人数");
        List<OptionalCourse> opCourseList = opCourseDao.queryOpCoursesByStuID(student.getId());
        opCourseList.forEach(System.out::println);

        System.out.println("必修课：");
        System.out.println("编号ID    课程    类型    已选课人数    教师ID    教师名称    学分");
        List<RequiredCourse> reCourseList = reCourseDao.queryAllReCourses();
        reCourseList.forEach(System.out::println);
        return 1;
    }

    @Override
    public int SelectOpCourse() {
        // 1、显示自己未选且人数未满的选修课清单
        List<OptionalCourse> opCourseList = opCourseDao.queryUnselectOpCoursesByStuID(student.getId());
        System.out.println("您可以选择的选修课：");
        System.out.println("编号ID    课程    类型    已选课人数    教师ID    教师名称    最大选课人数");
        opCourseList.forEach(System.out::println);

        // 2、学生选课
        System.out.println("请输入需要选择的课程编号：");
        Scanner sc = new Scanner(System.in);
        Integer courseID = sc.nextInt();
        return opCourseDao.studentSelectOpCourse(student.getId(), courseID);

    }

    @Override
    public int changePassword() {
        System.out.println("请输入密码；");
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();
        System.out.println("请再次确认密码：");
        String surePassword = sc.nextLine();

        //检查密码合法性
        if (surePassword.equals(password) && surePassword.length() >= 6 && surePassword.length() <= 18) {
            student.setPassword(surePassword);
            return studentDao.updateStudent(student);
        }
        System.out.println("修改失败！密码长度应为6~18");
        return -1;
    }
}
