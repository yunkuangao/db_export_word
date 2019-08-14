package com.pomzwj.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

/**
 * 类说明:数据库基础信息
 *
 * @author yuntian 317526763@qq.com
 * @date 2019年8月13日 13点34分
 */
@Data
public class DbBaseInfo implements Serializable{
    private String ip;
    private String port;
    private String dbName;
    private String userName;
    private String password;
    private String[] optional;

    public boolean haveOptional(String optional){
        return Collections.singletonList(optional).contains(optional);
    }
}
