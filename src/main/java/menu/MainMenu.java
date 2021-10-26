package menu;

import dao.impl.StudentDaoImpl;
import dao.impl.TeacherDaoImpl;
import bean.Student;
import bean.Teacher;
import bean.Admin;
import utils.JdbcUtils;

import java.util.Scanner;

/**
 * 项目启动入口，主菜单
 */
public class MainMenu {
    public static void main(String[] args) {

        //将费时的数据库连接池初始化提前
        System.out.println("系统启动中，请稍后~");
        JdbcUtils.getConnection();
        JdbcUtils.rollbackAndClose();

        while (true) {
            try {
                System.out.println("欢迎使用选课系统");
                System.out.println("0 管理员登陆    1 教师登陆    2 学生登陆    3 退出");
                Scanner sc = new Scanner(System.in);
                int choose = sc.nextInt();
                switch (choose) {
                    case 0: {   //管理员登陆
                        System.out.println("请输入您的管理员账号：");
                        String name = sc.next();
                        System.out.println("请输入您的管理员密码：");
                        String password = sc.next();
                        Admin admin = Admin.adminLogin(name, password);
                        if (admin != null) {   //进入相应用户的功能服务
                            new UserControllerMenu().adminControllerMenu(admin);
                        } else {
                            System.out.println("您输入的ID或密码错误！");
                        }
                        break;
                    }
                    case 1: {   //教师登录
                        System.out.println("请输入您的教师ID：");
                        Integer ID = sc.nextInt();
                        System.out.println("请输入您的教师密码：");
                        String password = sc.next();
                        Teacher teacher = new TeacherDaoImpl().teacherLogin(ID, password);
                        if (teacher != null) {
                            new UserControllerMenu().teacherControllerMenu(teacher);
                        } else {
                            System.out.println("您输入的ID或密码错误！");
                        }
                        break;
                    }
                    case 2: {   //学生登录
                        System.out.println("请输入您的学生ID：");
                        Integer ID = sc.nextInt();
                        System.out.println("请输入您的学生密码：");
                        String password = sc.next();
                        Student student = new StudentDaoImpl().studentLogin(ID, password);
                        if (student != null) {
                            new UserControllerMenu().studentControllerMenu(student);
                        } else {
                            System.out.println("您输入的ID或密码错误！");
                        }
                        break;
                    }
                    case 3: {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
    }
}
