package com.dbexport.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.stereotype.Component;

/**
 * 类说明:Druid连接池
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@Component
public class DruidUtils {

    /**
     * 获取数据源
     * @param jdbcUrl         jdbc
     * @param userName        用户名
     * @param password        密码
     * @param driverClassName 驱动名
     * @return 返回一个数据持久化连接池实例
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:50
     **/
    public DruidDataSource getDataSource(String jdbcUrl, String userName, String password, String driverClassName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMaxWait(10000L);
        dataSource.setInitialSize(8);
        dataSource.setTimeBetweenEvictionRunsMillis(3000);
        return dataSource;
    }
}
