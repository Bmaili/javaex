package service.impl;

import dao.OptionalCourseDao;
import dao.RequiredCourseDao;
import dao.StudentDao;
import dao.TeacherDao;
import dao.impl.OptionalCourseDaoImpl;
import dao.impl.RequiredCourseDaoImpl;
import dao.impl.StudentDaoImpl;
import dao.impl.TeacherDaoImpl;
import bean.OptionalCourse;
import bean.RequiredCourse;
import bean.Student;
import bean.Teacher;
import service.TeacherService;

import java.util.List;
import java.util.Scanner;

/**
 * 提供教师服务的实现类
 */
public class TeacherServiceImpl implements TeacherService {

    static TeacherDao teacherDao = new TeacherDaoImpl();
    static StudentDao studentDao = new StudentDaoImpl();
    static OptionalCourseDao opCourseDao = new OptionalCourseDaoImpl();
    static RequiredCourseDao reCourseDao = new RequiredCourseDaoImpl();

    private Teacher teacher;

    public TeacherServiceImpl(Teacher teacher) {
        this.teacher = teacher;
    }


    @Override
    public int showMyCourses() {   //依次展示选修课、必修课
        System.out.println("您所教授的选修课：");
        System.out.println("编号    课程    类型    已选课人数    教师ID    教师名称    最大选课人数");
        List<OptionalCourse> opCourseList = opCourseDao.queryOpCoursesByTeaID(teacher.getId());
        opCourseList.forEach(System.out::println);

        System.out.println("您所教授的必修课：");
        System.out.println("编号    课程    类型    已选课人数    教师ID    教师名称    学分");
        List<RequiredCourse> reCourseList = reCourseDao.queryReCoursesByTeaID(teacher.getId());
        reCourseList.forEach(System.out::println);
        return 1;
    }

    @Override
    public int showOneCourseStudents() {
        System.out.println("请输入课程类型：(0必修课，1选修课)");
        Scanner sc = new Scanner(System.in);
        int CouType = sc.nextInt();
        if (CouType == 0) {//必修课
            System.out.println("请输入课程编号:");
            Integer CourseID = sc.nextInt();
            if (teacherDao.hasTheReCourse(teacher.getId(), CourseID)) {//鉴权
                System.out.println("该课程的学生有：");
                System.out.println("ID      姓名     班级");
                List<Student> students = studentDao.queryAllStudents();
                students.forEach(System.out::println);
                return 1;
            } else {
                System.out.println("这不是您所教授的课程。");
            }
        }
        if (CouType == 1) {//选修课
            System.out.println("请输入课程编号:");
            Integer CourseID = sc.nextInt();
            if (teacherDao.hasTheOpCourse(teacher.getId(), CourseID)) {//鉴权
                System.out.println("该课程的学生有：");
                System.out.println("ID      姓名     班级");
                List<Student> students = studentDao.queryStudentsByOpCourseId(CourseID);
                students.forEach(System.out::println);
                return 1;
            } else {
                System.out.println("这不是您所教授的课程。");
            }
        }
        return -1;
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
            teacher.setPassword(surePassword);
            return teacherDao.updateTeacher(teacher);

        }
        System.out.println("修改失败！密码长度应为6~18");
        return -1;
    }
}
