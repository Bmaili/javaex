package bean;

/**
 * 选修课类
 */
public class OptionalCourse extends Course {
    private Integer maxStuNum;  //最大学生数量

    public OptionalCourse() {
    }

    public OptionalCourse(Integer id, String name, Integer type, Integer stuNum, Integer teacherID, String teacher, Integer maxStuNum) {
        super(id, name, type, stuNum, teacherID, teacher);
        this.maxStuNum = maxStuNum;
    }

    public Integer getMaxStuNum() {
        return maxStuNum;
    }

    public void setMaxStuNum(Integer maxStuNum) {
        this.maxStuNum = maxStuNum;
    }

    @Override
    public String toString() {
        return super.toString() + "    " + this.maxStuNum;
    }
}
