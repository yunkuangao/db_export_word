package com.dbexport.constant;

/**
 * 得到数据库链接属性
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
public class DbExportConstants {

    /**
     * 得到连接地址
     * @param dbKind 数据库类型
     * @param ip     ip
     * @param port   端口
     * @param dbName 数据库名
     * @return 返回相应的数据库链接
     */
    public static String getJdbcUrl(String dbKind, String ip, String port, String dbName) {
        String url = null;
        switch (dbKind.toUpperCase()) {
            //MySQL数据库
            case "MYSQL":
                url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
                break;
            //oracle数据库
            case "ORACLE":
                url = "jdbc:oracle:thin:@//" + ip + ":" + port + "/" + dbName;
                break;
            //SQL SERVER数据库
            case "SQLSERVER":
                url = "jdbc:sqlserver://" + ip + ":" + port + ";databaseName=" + dbName;
                break;
            default:
        }
        return url;
    }

    /**
     * 得到驱动名称
     * @param dbKind 数据库类型
     * @return 返回相应的驱动名称
     */
    public static String getDriverClassName(String dbKind) {
        String driverClassName = null;
        switch (dbKind.toUpperCase()) {
            case "MYSQL":
                driverClassName = "com.mysql.jdbc.Driver";
                break;
            case "ORACLE":
                driverClassName = "oracle.jdbc.driver.OracleDriver";
                break;
            case "SQLSERVER":
                driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                break;
            default:
        }
        return driverClassName;
    }

    /**
     * 获取得到所有表和表注释的sql语句
     * @param dbKind 数据库类型
     * @param dbName 数据库名称
     * @return 返回数据库用户名下的所有表名
     */
    public static String getTableName(String dbKind, String dbName) {
        String sql = null;
        switch (dbKind.toUpperCase()) {
            case "MYSQL":
                sql = "    SELECT table_name TABLE_NAME, table_comment COMMENTS" +
                        "    FROM information_schema.tables" +
                        "    WHERE table_schema = '" + dbName + "'" +
                        "      AND table_type = 'base table'";
                break;
            case "ORACLE":
                sql = " select t1.TABLE_NAME," +
                        " t2.COMMENTS " +
                        " from user_tables t1 ,user_tab_comments t2 " +
                        " where t1.table_name = t2.table_name(+)";
                break;
            case "SQLSERVER":
                sql = "    SELECT TABLE_NAME = d.name, COMMENTS = f.value" +
                        "    FROM sysobjects d" +
                        "             LEFT JOIN sys.extended_properties f ON d.id = f.major_id AND f.minor_id = 0" +
                        "    WHERE d.xtype = 'u'" +
                        "      AND d.name != 'sysdiagrams'";
                break;
            default:
        }
        return sql;
    }

    /**
     * 得到查询表字段和参数的sql
     * @param dbKind    数据库类型
     * @param tableName 表名
     * @return 返回对应的表字段和参数
     */
    public static String getColNameInfo(String dbKind, String tableName) {
        String sql = null;
        switch (dbKind.toUpperCase()) {
            case "MYSQL":
                sql = " SELECT c.column_name                                           COLUMN_NAME," +
                        "       concat(c.data_type,'(',c.character_maximum_length ,')')     DATA_TYPE," +
                        "       CASE WHEN c.is_nullable = 'NO' THEN 'N' ELSE '' END  AS NULLABLE," +
                        "       c.column_default                                        DATA_DEFAULT," +
                        "       c.column_comment                                        COMMENTS," +
                        "       CASE WHEN p.column_Name IS NULL THEN '' ELSE 'Y' END AS PK" +
                        " FROM information_schema.COLUMNS c" +
                        "         LEFT JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS p" +
                        " ON C.table_schema = p.table_schema AND C.table_name = p.TABLE_NAME AND C.COLUMN_NAME = p.COLUMN_NAME AND p.constraint_name = 'PRIMARY'" +
                        " WHERE C.table_name = '" + tableName + "' AND C.table_schema = (SELECT DATABASE ())" +
                        " ORDER BY C.ordinal_position";
                break;
            case "ORACLE":
                sql = " SELECT utc.column_name                     COLUMN_NAME" +
                        "     , CASE" +
                        "           WHEN utc.data_type = 'DATE'" +
                        "               THEN utc.data_type" +
                        "           WHEN utc.data_type = 'CLOB'" +
                        "               THEN utc.data_type" +
                        "           WHEN utc.data_type = 'BLOB'" +
                        "               THEN utc.data_type" +
                        "           WHEN utc.data_type = 'NUMBER'" +
                        "               THEN CASE" +
                        "                        WHEN utc.DATA_PRECISION IS NOT NULL AND utc.DATA_SCALE IS NOT NULL AND utc.DATA_SCALE != 0" +
                        "                            THEN utc.data_type || '(' || utc.DATA_PRECISION || ',' || utc.DATA_SCALE || ')'" +
                        "                        WHEN utc.DATA_PRECISION IS NOT NULL" +
                        "                            THEN utc.data_type || '(' || utc.DATA_PRECISION || ')'" +
                        "                        ELSE utc.data_type END" +
                        "           ELSE utc.data_type || '(' || utc.data_length || ')'" +
                        "    END                AS                  DATA_TYPE" +
                        "     , CASE" +
                        "           WHEN UTC.COLUMN_NAME in (SELECT col.column_name " +
                        "                 FROM user_constraints con," +
                        "                      user_cons_columns col" +
                        "                 WHERE con.constraint_name = col.constraint_name" +
                        "                   AND con.constraint_type = 'P'" +
                        "                   AND col.table_name = '" + tableName + "') " +
                        "               THEN '主键'" +
                        "           ELSE '' END AS                  PK" +
                        "     , DECODE(utc.nullable, 'N', '必填', '') NULLABLE" +
                        "     , utc.data_default                    DATA_DEFAULT" +
                        "     , ucc.comments                        COMMENTS" +
                        " FROM user_tab_columns utc," +
                        "     user_col_comments ucc" +
                        " WHERE utc.table_name = ucc.table_name" +
                        "  AND utc.column_name = ucc.column_name" +
                        "  AND utc.table_name = '" + tableName + "'" +
                        " ORDER BY column_id";
                break;
            case "SQLSERVER":
                sql = "    SELECT cast(a.name AS varchar(100))                                          AS COLUMN_NAME," +
                        "           cast(b.name AS varchar(100))  +'('  +   cast(columnproperty(a.id, a.name, 'PRECISION') AS varchar(100))  AS DATA_TYPE," +
                        "           cast(CASE WHEN a.isnullable = 1 THEN '' ELSE 'N' END AS varchar(100)) AS NULLABLE," +
                        "           cast(isnull(e.text, '') AS varchar(100))                              AS DATA_DEFAULT," +
                        "           cast(isnull(g.[VALUE], '') AS varchar(100))                           AS COMMENTS," +
                        "           cast(CASE WHEN t4.id IS NULL THEN '' ELSE 'Y' END AS varchar(100))    AS PK" +
                        "    FROM sys.syscolumns a" +
                        "             LEFT JOIN sys.SYSINDEXKEYS t4 ON a.colid = t4.colid AND t4.id = a.id" +
                        "             LEFT JOIN sys.systypes b ON a.xusertype = b.xusertype" +
                        "             INNER JOIN sys.sysobjects d ON a.id = d.id AND d.xtype = 'U' AND d.name <> 'dtproperties'" +
                        "             LEFT JOIN sys.syscomments e ON a.cdefault = e.id" +
                        "             LEFT JOIN sys.extended_properties g ON a.id = g.major_id AND a.colid = g.minor_id" +
                        "             LEFT JOIN sys.extended_properties f ON d.id = f.major_id AND f.minor_id = 0" +
                        "    WHERE d.name = '" + tableName + "'";
                break;
            default:
        }
        return sql;
    }
}
