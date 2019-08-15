package com.pomzwj.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类说明:数据库基础信息
 *
 * @author yuntian 317526763@qq.com
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

    @Override
    public String toString() {
        return ip + port + dbName + userName + password + Arrays.toString(optional.toArray()) + Arrays.toString(table.toArray());
    }

    public List<String> getDBInfo() {
        return Arrays.asList(port, dbName, userName, password);
    }
}
