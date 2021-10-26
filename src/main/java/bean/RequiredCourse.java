package bean;

/**
 * 必修课类
 */
public class RequiredCourse extends Course {
    private Integer credit;  //学分

    public RequiredCourse() {
    }

    public RequiredCourse(Integer id, String name, Integer type, Integer stuNum, Integer teacherID, String teacher, Integer credit) {
        super(id, name, type, stuNum, teacherID, teacher);
        this.credit = credit;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return super.toString() + "    " + this.credit;
    }
}
