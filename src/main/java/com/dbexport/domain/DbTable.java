package com.dbexport.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 类说明:
 *
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@Data
public class DbTable implements Serializable {
    //表名
    private String tableName;
    //表注释
    private String tableComments;
    //表字段
    private List<Map> tabsColumn;
}
