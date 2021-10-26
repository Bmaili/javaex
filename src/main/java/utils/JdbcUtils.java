package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Jdbc工具类。功能：创建连接池、提供连接、事务提交、回滚等
 */
public class JdbcUtils {

    private static DruidDataSource dataSource;
    //用ThreadLocal对象记录Connection对象，保证同一线程不同时刻获得的Connection对象为同一个
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            // 创建Druid数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            System.out.println("jdbc.properties 配置信息错误");
        }
    }

    /**
     * 获取数据库连接池中的连接
     *
     * @return 获取连接失败返回null, 否则返回Connection实例
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();//从数据库连接池中获取连接
                conns.set(conn); // 保存到ThreadLocal对象中，供后面的jdbc操作使用
                conn.setAutoCommit(false); // 设置为手动管理事务
            } catch (SQLException e) {
                System.out.println("sql语句错误");
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭释放连接
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明之前使用过连接
            try {
                connection.commit(); // 提交事务
            } catch (SQLException e) {
                System.out.println("sql语句错误");

            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("sql语句错误");

                }
            }
        }
        conns.remove();
    }

    /**
     * 回滚事务并关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明之前使用过连接
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("sql语句错误");

                }
            }
        }
        conns.remove();
    }
}

