package com.dbexport.service.impl;

import com.dbexport.constant.DbExportConstants;
import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;
import com.dbexport.service.IDataOperatorService;
import com.dbexport.utils.DbConnection;
import com.dbexport.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类说明:数据操作类
 *
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@Service
public class DataOperatorServiceImpl implements IDataOperatorService {


    @Override
    public List<DbTable> getTableName(String dbKind, DbBaseInfo info) throws Exception {
        List<DbTable> tableList = new ArrayList<>();
        Connection connection = DbConnection.getConn(DbExportConstants.getJdbcUrl(dbKind, info.getIp(), info.getPort(), info.getDbName()), info.getUserName(), info.getPassword(), DbExportConstants.getDriverClassName(dbKind));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(DbExportConstants.getTableNameSQL(dbKind, info.getDbName()));
        while (resultSet.next()) {
            DbTable dbTable = new DbTable();
            String tableName = resultSet.getString("TABLE_NAME");
            String tableComments = resultSet.getString("COMMENTS");
            dbTable.setTableComments(tableComments);
            dbTable.setTableName(tableName);
            tableList.add(dbTable);
        }
        DbConnection.closeConn(connection);
        return tableList;
    }

    @Override
    public List<Map> getTabsColumn(String dbKind, DbBaseInfo info, String tableName) throws Exception {
        List<Map> list = new ArrayList<>();
        List<String> key = info.getOptional();
        Connection connection = DbConnection.getConn(DbExportConstants.getJdbcUrl(dbKind, info.getIp(), info.getPort(), info.getDbName()), info.getUserName(), info.getPassword(), DbExportConstants.getDriverClassName(dbKind));

        Statement statement = connection.createStatement();
        String sql = DbExportConstants.getColNameInfoSQL(dbKind, tableName);
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Map<String, String> colDataMap = new HashMap<>(16);
            for(String str : key){
                colDataMap.put(str, resultSet.getString(str));
            }
            list.add(colDataMap);
        }
        DbConnection.closeConn(connection);
        return list;
    }

    @Override
    public List<DbTable> getTabsAllColumn(List<DbTable> tableMessage, String dbKind, DbBaseInfo info) throws Exception {
        tableMessage = tableMessage
                .stream()
                .parallel()
                .filter(lt -> info.getTable().contains(lt.getTableName()))
                .collect(Collectors.toList());
        for (DbTable dbTable : tableMessage) {
            List<Map> tabsColumn = getTabsColumn(dbKind, info, dbTable.getTableName());
            dbTable.setTabsColumn(tabsColumn);
        }
        return tableMessage;
    }

    @Override
    public List<DbTable> getTabsAllColumn(String dbKind, DbBaseInfo info) throws Exception {
        List<DbTable> tableMessage = getTableName(dbKind, info);
        return getTabsAllColumn(tableMessage, dbKind, info);
    }
}
