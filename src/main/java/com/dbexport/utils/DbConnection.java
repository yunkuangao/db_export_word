package com.dbexport.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC连接
 * @author yuntian 317526763@qq.com
 * @version 1.0
 * @date 2019年8月19日 09点47分
 */
public class DbConnection {
    /**
     * 获取连接
     * @param jdbcUrl         jdbc
     * @param userName        用户名
     * @param password        密码
     * @param driverClassName 驱动名
     * @return 返回一个连接实例
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:48
     **/
    public static Connection getConn(String jdbcUrl, String userName, String password, String driverClassName) throws Exception {
        Class.forName(driverClassName);
        return DriverManager.getConnection(jdbcUrl, userName, password);
    }

    /**
     * 关闭资源
     * @param rs 结果集实例
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:48
     **/
    public static void closeRs(ResultSet rs) throws Exception {
        if (rs != null) {
            Statement statement = rs.getStatement();
            if (statement != null) {
                Connection connection = statement.getConnection();
                if (connection != null) {
                    rs.close();
                    statement.close();
                    connection.close();
                }
            }
        }
        System.out.println("已关闭数据库连接");
    }

    /**
     * 关闭语句
     * @param statement 语句实例
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:49
     **/
    public static void closeStat(Statement statement) throws Exception {
        if (statement != null) {
            Connection connection = statement.getConnection();
            if (connection != null) {
                statement.close();
                connection.close();
            }
        }
        System.out.println("已关闭数据库连接");

    }

    /**
     * 关闭连接
     * @param connection 连接实例
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:49
     **/
    public static void closeConn(Connection connection) throws Exception {
        if (connection != null) {
            connection.close();
        }
        System.out.println("已关闭数据库连接");
    }
}
