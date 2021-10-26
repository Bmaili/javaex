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
import bean.Admin;
import service.AdminService;
import utils.JdbcUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * 提供管理员服务的实现类
 */
public class AdminServiceImpl implements AdminService {
    static StudentDao studentDao = new StudentDaoImpl();
    static TeacherDao teacherDao = new TeacherDaoImpl();
    static OptionalCourseDao opCourseDao = new OptionalCourseDaoImpl();
    static RequiredCourseDao reCourseDao = new RequiredCourseDaoImpl();

    static String userDefaultPass = null;

    //从配置文件中读取默认用户密码
    static {
        try {
            Properties properties = new Properties();
            // 读取 admin.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("admin.properties");
            properties.load(inputStream);
            userDefaultPass = properties.getProperty("userDefaultPass");
        } catch (Exception e) {
            System.out.println("admin.properties 配置信息错误");
        }
    }

    private Admin admin;

    public AdminServiceImpl(Admin admin) {
        this.admin = admin;
    }


    @Override
    public int addTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入教师的姓名：");
        String teacherName = sc.nextLine();
        System.out.println("请输入教师的职称：");
        String teacherLevel = sc.nextLine();

        Teacher teacher = new Teacher(null, teacherName, userDefaultPass, teacherLevel);
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public int addStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生的姓名：");
        String studentName = sc.nextLine();
        System.out.println("请输入学生的班级：");
        String studentClass = sc.nextLine();

        Student student = new Student(null, studentName, userDefaultPass, studentClass);
        if (studentDao.addStudent(student) != -1)   //添加学生：1、数据库添加该生记录 2、所有必修课的学生人数加一
            return reCourseDao.incStuNumOfAllReCourses();
        return -1;
    }

    @Override
    public int deleteTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入教师的ID：");
        int teacherID = sc.nextInt();
        return teacherDao.deleteTeacherById(teacherID);
    }

    @Override
    public int deleteStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生的ID：");
        int studentID = sc.nextInt();

        //删除学生：1、所有必修课学生人数减一 2、该生所选的所有选修课的学生人数减一
        //3、删除该生与所有选修课的关联记录 4、删除该学生
        if (reCourseDao.subStuNumOfAllReCourses() != -1)
            if (opCourseDao.subStuNumOfReCoursesByStudentID(studentID) != -1)
                if (opCourseDao.deleteStuSelectReCourseByStudentID(studentID) != -1)
                    return studentDao.deleteStudentById(studentID);
        return -1;
    }

    @Override
    public int addCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入课程名：");
        String name = sc.next();
        System.out.println("请输入课程类型（0必修，1选修）：");
        int type = sc.nextInt();
        System.out.println("请输入上课教师ID：");
        Integer teacherID = sc.nextInt();
        if (type == 0) {
            System.out.println("请输入学分：");
            int credit = sc.nextInt();
            return reCourseDao.addRequiredCourse(new RequiredCourse(null, name, type, null, teacherID, null, credit));
        }
        if (type == 1) {
            System.out.println("请输入最大选课人数：");
            int maxstu = sc.nextInt();
            return opCourseDao.addOptionalCourse(new OptionalCourse(null, name, type, null, teacherID, null, maxstu));
        }
        return -1;
    }

    @Override
    public int deleteCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要删除的课程类型（0必修，1选修）：");
        int type = sc.nextInt();
        if (type == 0) {
            System.out.println("请输入课程ID：");
            Integer reCourseID = sc.nextInt();   //因为必修课所有的学生都选，所以无需记录学生与必修课的关系
            return reCourseDao.deleteReCourseById(reCourseID);
        }
        if (type == 1) {
            System.out.println("请输入课程ID：");
            Integer opCourseID = sc.nextInt();   //删除选修课：1、删除该课的学生选课记录 2、删除该选修课
            if (opCourseDao.deleteStuSelectReCourseByCourseID(opCourseID) != -1) {
                return opCourseDao.deleteOpCourseById(opCourseID);
            }
        }
        return -1;
    }

    @Override
    public int descSortOptionalCourseByStuNum() {
        System.out.println("选修课按人数降序排列如下：");
        System.out.println("编号ID    课程    类型    已选课人数    教师ID    教师名称    最大选课人数");
        List<OptionalCourse> opCourseList = opCourseDao.queryDescSortedOpCoursesByStuNum();
        opCourseList.forEach(System.out::println);
        return 1;
    }

    @Override
    public int showAllCoursesList() {
        System.out.println("必修课列表：");
        System.out.println("编号ID    课程    类型    已选课人数    教师ID    教师名称    学分");
        List<RequiredCourse> reCourseList = reCourseDao.queryAllReCourses();
        reCourseList.forEach(System.out::println);

        System.out.println("选修课列表：");
        System.out.println("编号ID    课程    类型    已选课人数    教师ID    教师名称    最大选课人数");
        List<OptionalCourse> opCourseList = opCourseDao.queryAllOpCourses();
        opCourseList.forEach(System.out::println);
        return 1;
    }

    @Override
    public int changeCouTeacher() {
        System.out.println("请输入需要修改的课程类型（0必修，1选修）：");
        Scanner sc = new Scanner(System.in);
        int type = sc.nextInt();
        if (type == 0) {
            System.out.println("请输入课程ID");
            int courseID = sc.nextInt();
            RequiredCourse reCourse = reCourseDao.queryReCourseByID(courseID);
            System.out.println("请输入教师ID");
            int teacherID = sc.nextInt();   //获得相应的教师Bean与课程Bean
            Teacher teacher = teacherDao.queryTeacherById(teacherID);
            if (teacher != null) {
                String teacherName = teacher.getName();
                reCourse.setTeacherId(teacherID);
                reCourse.setTeacher(teacherName);       //更新
                return reCourseDao.updateReCourseByID(reCourse);
            }
        }
        if (type == 1) {
            System.out.println("请输入课程ID");
            int courseID = sc.nextInt();
            OptionalCourse opCourse = opCourseDao.queryOpCourseByID(courseID);
            System.out.println("请输入教师ID");
            int teacherID = sc.nextInt();
            Teacher teacher = teacherDao.queryTeacherById(teacherID);
            if (teacher != null) {
                String teacherName = teacher.getName();
                opCourse.setTeacherId(teacherID);
                opCourse.setTeacher(teacherName);
                return opCourseDao.updateOpCourseByID(opCourse);
            }
        }
        return -1;
    }

    @Override
    public int showStudentsList() {
        System.out.println("以下是所有的学生信息：");
        System.out.println("ID      姓名     班级");
        List<Student> students = studentDao.queryAllStudents();
        students.forEach(System.out::println);
        return 1;
    }

    @Override
    public int showTeachersList() {
        System.out.println("以下是所有的教师信息：");
        System.out.println("ID     姓名    职称");
        List<Teacher> teachers = teacherDao.queryAllTeachers();
        teachers.forEach(System.out::println);
        return 1;
    }

    @Override
    public int resetUserPassword() {
        System.out.println("请输入需要重置密码的是教师还是学生（0教师，1学生）：");
        Scanner sc = new Scanner(System.in);
        int type = sc.nextInt();
        if (type == 0) {
            System.out.println("请输入教师的ID");
            int teacherID = sc.nextInt();
            Teacher teacher = teacherDao.queryTeacherById(teacherID);
            if (teacher != null) {   //查询到该用户后将密码设置为默认用户密码，再将Bean更新至数据库
                teacher.setPassword(userDefaultPass);
                return teacherDao.updateTeacher(teacher);
            }
        }
        if (type == 1) {
            System.out.println("请输入学生的ID");
            int studentID = sc.nextInt();
            Student student = studentDao.queryStudentById(studentID);
            if (student != null) {
                student.setPassword(userDefaultPass);
                return studentDao.updateStudent(student);
            }
        }
        return -1;
    }
}