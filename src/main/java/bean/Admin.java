package bean;

import java.io.InputStream;
import java.util.Properties;

/**
 * 管理员类，单例模式，类加载时从admin.properties获得管理员账号与密码
 */
public final class Admin {

    private String name;  //管理员姓名
    private String password;   //管理员密码

    static private String propertiesAdminName;   //从配置文件读取的姓名
    static private String propertiesPassword;   //从配置文件读取的密码

    //读取配置信息
    static {
        try {
            Properties properties = new Properties();
            // 读取 admin.properties属性配置文件
            InputStream inputStream = Admin.class.getClassLoader().getResourceAsStream("admin.properties");
            properties.load(inputStream);
            propertiesAdminName = properties.getProperty("adminName");
            propertiesPassword = properties.getProperty("adminPassword");
        } catch (Exception e) {
            System.out.println("admin.properties 配置信息错误");
        }
    }

    //单例模式
    private Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private static class AdminInstance {
        private static final Admin INSTANCE = new Admin(propertiesAdminName, propertiesPassword);
    }

    //获得Admin实例的唯一public方法
    public static Admin adminLogin(String adminName, String password) {
        if (propertiesAdminName.equals(adminName) && propertiesPassword.equals(password))
            return AdminInstance.INSTANCE;
        else
            return null;
    }
}

