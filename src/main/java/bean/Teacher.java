package bean;

/**
 * 教师类
 */
public class Teacher extends User {
    private String level;   //教师职称

    public Teacher() {
    }

    public Teacher(Integer id, String name, String password, String level) {
        super(id, name, password);
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return super.toString() + "    " + level;
    }
}
