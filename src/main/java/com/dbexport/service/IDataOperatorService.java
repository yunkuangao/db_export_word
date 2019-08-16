package com.dbexport.service;

import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;

import java.util.List;
import java.util.Map;

/**
 * 类说明:数据操作服务接口
 *
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
public interface IDataOperatorService {
    /**
     * 获取表名
     *
     * @param dbKind 1
     * @param info   1
     * @return 1
     * @throws Exception 1
     */
    List<DbTable> getTableName(String dbKind, DbBaseInfo info) throws Exception;

    /**
     * 获取列
     *
     * @param dbKind    1
     * @param tableName 1
     * @param info      1
     * @return 1
     * @throws Exception 1
     */
    List<Map> getTabsColumn(String dbKind, DbBaseInfo info, String tableName) throws Exception;

    /**
     * @Author yuntian 317526763@qq.com
     * @Description //TODO
     * @Date 13:12 2019/8/16
     * @Param [dbKind, info]
     * @return java.util.List<com.dbexport.domain.DbTable>
     **/
    List<DbTable> getTabsAllColumn(String dbKind, DbBaseInfo info) throws Exception;
    
    /**
     * 获取列
     *
     * @param dbKind    1
     * @param tableMessage 1
     * @param info      1
     * @return 1
     * @throws Exception 1
     */
    List<DbTable> getTabsAllColumn(List<DbTable> tableMessage,String dbKind, DbBaseInfo info) throws Exception;
}
