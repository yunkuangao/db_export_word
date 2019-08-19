package com.dbexport.service;

import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;

import java.util.List;
import java.util.Map;

/**
 * 数据操作服务接口
 * @author yuntian 317526763@qq.com
 * @version 1.0
 * @date 2018/10/29/0029.
 */
public interface IDataOperatorService {
    /**
     * 获取表名
     * @param dbKind 数据库类型
     * @param info   参数
     * @return 返回表名
     */
    List<DbTable> getTableName(String dbKind, DbBaseInfo info) throws Exception;

    /**
     * 获取列
     * @param dbKind    数据库类型
     * @param tableName 表名
     * @param info      参数
     * @return 返回表的列
     */
    List<Map> getTabsColumn(String dbKind, DbBaseInfo info, String tableName) throws Exception;

    /**
     * 获取所有列
     * @param dbKind 数据库类型
     * @param info   参数
     * @return 返回所有列
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:44
     **/
    List<DbTable> getTabsAllColumn(String dbKind, DbBaseInfo info) throws Exception;

    /**
     * 获取所有列
     * @param tableMessage 表信息
     * @param dbKind       数据库类型
     * @param info         参数
     * @return 返回所有列
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:45
     **/
    List<DbTable> getTabsAllColumn(List<DbTable> tableMessage, String dbKind, DbBaseInfo info) throws Exception;
}
