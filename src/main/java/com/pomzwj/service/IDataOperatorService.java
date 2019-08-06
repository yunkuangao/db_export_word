package com.pomzwj.service;

import com.pomzwj.domain.DbBaseInfo;
import com.pomzwj.domain.DbTable;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * 类说明:数据操作服务接口
 * @author zhaowenjie<1 5 1 3 0 4 1 8 2 0 @ qq.com>
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
     * @param dbKind     1
     * @param tableName  1
     * @param connection 1
     * @return 1
     * @throws Exception 1
     */
    List<Map> getTabsColumn(String dbKind, String tableName, Connection connection) throws Exception;
}
