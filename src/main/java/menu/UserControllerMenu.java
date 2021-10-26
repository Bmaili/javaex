package menu;

import bean.Student;
import bean.Teacher;
import bean.Admin;
import service.AdminService;
import service.StudentService;
import service.TeacherService;
import service.impl.AdminServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.TeacherServiceImpl;
import utils.ServiceProxy;

import java.lang.reflect.Proxy;
import java.util.Scanner;

/**
 * 不同用户的操作菜单类
 */
public class UserControllerMenu {

    public void teacherControllerMenu(Teacher teacher) {   //教师服务

        //AOP思想，通过代理来处理数据库的提交、回滚、异常处理等操作
        TeacherServiceImpl teacherSer = new TeacherServiceImpl(teacher);
        TeacherService teaSerPro = (TeacherService) Proxy.newProxyInstance(MainMenu.class.getClassLoader(), new Class[]{TeacherService.class}, new ServiceProxy(teacherSer));

        System.out.println("欢迎登陆！" + teacher.getName());
        while (true) {
            System.out.println("0 查看我教授的课程    1 查看某课中的学生信息    2 修改密码    3 退出");
            System.out.println("请选择您想执行的操作：");
            Scanner sc = new Scanner(System.in);
            int choose = sc.nextInt();
            switch (choose) {
                case 0: {
                    teaSerPro.showMyCourses();
                    break;
                }
                case 1: {
                    teaSerPro.showOneCourseStudents();
                    break;
                }
                case 2: {
                    teaSerPro.changePassword();
                    break;
                }
                case 3: {
                    return;
                }
            }
        }
    }

    public void studentControllerMenu(Student student) {   //学生服务

        //代理模式
        StudentServiceImpl studentSer = new StudentServiceImpl(student);
        StudentService stuSerPro = (StudentService) Proxy.newProxyInstance(MainMenu.class.getClassLoader(), new Class[]{StudentService.class}, new ServiceProxy(studentSer));

        System.out.println("欢迎登陆！" + student.getName());
        while (true) {
            System.out.println("0 查看我的课程    1 选课    2 修改密码    3 退出");
            System.out.println("请选择您想执行的操作：");
            Scanner sc = new Scanner(System.in);
            int choose = sc.nextInt();
            switch (choose) {
                case 0: {
                    stuSerPro.showMyCourses();
                    break;
                }
                case 1: {
                    stuSerPro.SelectOpCourse();
                    break;
                }
                case 2: {
                    stuSerPro.changePassword();
                    break;
                }
                case 3: {
                    return;
                }
            }
        }
    }

    public void adminControllerMenu(Admin admin) {   //管理员服务

        //代理模式
        AdminServiceImpl adminSer = new AdminServiceImpl(admin);
        AdminService adminSerPro = (AdminService) Proxy.newProxyInstance(MainMenu.class.getClassLoader(), new Class[]{AdminService.class}, new ServiceProxy(adminSer));

        System.out.println("欢迎登陆！ 管理员");
        while (true) {
            System.out.println("0 查看所有课程    1 添加课程    2 删除课程");
            System.out.println("3 查看所有教师    4 添加教师    5 删除教师");
            System.out.println("6 查看所有学生    7 添加学生    8 删除学生");
            System.out.println("9 以人数排序查看选修课   10 更改课程的授课教师");
            System.out.println("11 重置教师、学生密码    12 退出");
            System.out.println("请选择您想执行的操作：");
            Scanner sc = new Scanner(System.in);
            int choose = sc.nextInt();
            switch (choose) {
                case 0: {
                    adminSerPro.showAllCoursesList();
                    break;
                }
                case 1: {
                    adminSerPro.addCourse();
                    break;
                }
                case 2: {
                    adminSerPro.deleteCourse();
                    break;
                }
                case 3: {
                    adminSerPro.showTeachersList();
                    break;
                }
                case 4: {
                    adminSerPro.addTeacher();
                    break;
                }
                case 5: {
                    adminSerPro.deleteTeacher();
                    break;
                }
                case 6: {
                    adminSerPro.showStudentsList();
                    break;
                }
                case 7: {
                    adminSerPro.addStudent();
                    break;
                }
                case 8: {
                    adminSerPro.deleteStudent();
                    break;
                }
                case 9: {
                    adminSerPro.descSortOptionalCourseByStuNum();
                    break;
                }
                case 10: {
                    adminSerPro.changeCouTeacher();
                    break;
                }
                case 11: {
                    adminSerPro.resetUserPassword();
                    break;
                }
                case 12: {
                    return;
                }
            }
        }
    }
}
