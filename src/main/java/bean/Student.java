package bean;

/**
 * 学生类
 */
public class Student extends User {
    private String stuClass;   //学生所在班级

    public Student() {
    }

    public Student(Integer id, String name, String password, String stuClass) {
        super(id, name, password);
        this.stuClass = stuClass;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    @Override
    public String toString() {
        return super.toString() + "    " + stuClass;
    }
}
