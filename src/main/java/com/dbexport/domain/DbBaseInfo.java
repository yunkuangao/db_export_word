package com.dbexport.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 类说明:数据库基础信息
 * @author yuntian 317526763@qq.com
 * @version 1.0
 * @date 2019年8月13日 13点34分
 */
@Data
public class DbBaseInfo implements Serializable {
    private String ip;
    private String port;
    private String dbName;
    private String userName;
    private String password;
    private List<String> optional;
    private List<String> table;

    /**
     * toString
     * @return java.lang.String
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:26
     **/
    @Override
    public String toString() {
        return ip + port + dbName + userName + password + Arrays.toString(optional.toArray()) + Arrays.toString(table.toArray());
    }

    /**
     * @return 返回一个数据库的基本信息
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:26
     **/
    public List<String> getDBInfo() {
        return Arrays.asList(port, dbName, userName, password);
    }
}
