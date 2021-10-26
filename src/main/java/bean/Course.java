package bean;

/**
 * 选修课与必修课的抽象父类
 */
public abstract class Course {
    protected Integer id;   //课程ID编号
    protected String name;   //课程名字
    protected Integer type;   //课程类型（0必修，1选修）
    protected Integer stuNum;  //当前此课的学生数量
    protected Integer teacherId;  //授课教师ID
    protected String teacher;   //授课教师姓名

    public Course() {
    }

    public Course(Integer id, String name, Integer type, Integer stuNum, Integer teacherId, String teacher) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stuNum = stuNum;
        this.teacherId = teacherId;
        this.teacher = teacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return id + "    " + name + "    " + (type == 0 ? "必修" : "选修") + "    " + stuNum + "    " + teacher;
    }
}
