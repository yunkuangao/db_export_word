package com.dbexport.constant;

/**
 * 类说明:常用字段
 *
 * @author zhaowenjie<1 5 1 3 0 4 1 8 2 0 @ qq.com>
 * @date 2018/10/29/0029.
 * @updator yuntian 317526763@qq.com
 */

public class DbExportConstants {

    /**
     * 得到连接地址
     *
     * @param dbKind 1
     * @param ip     1
     * @param port   1
     * @param dbName 1
     * @return 1
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
     *
     * @param dbKind 1
     * @return 1
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
     * 获取得到所有表和表注释的SQL语句
     *
     * @param dbKind               1
     * @param dbName--mysql需要数据库名称
     * @return 1
     */
    public static String getTableNameSQL(String dbKind, String dbName) {
        String sql = null;
        switch (dbKind.toUpperCase()) {
            case "MYSQL":
                sql = "select table_name TABLE_NAME, table_comment COMMENTS from information_schema.tables where table_schema='" + dbName + "' and table_type='base table'";
                break;
            case "ORACLE":
                sql = "select t1.TABLE_NAME," +
                        "t2.COMMENTS " +
                        "from user_tables t1 ,user_tab_comments t2 " +
                        "where t1.table_name = t2.table_name(+)";
                break;
            case "SQLSERVER":
                sql = "select TABLE_NAME=d.name,COMMENTS=f.value  from sysobjects d left join sys.extended_properties f on d.id=f.major_id and f.minor_id=0 where d.xtype = 'u' and d.name != 'sysdiagrams'";
                break;
            default:
        }
        return sql;
    }

    /**
     * 得到SQL
     *
     * @param dbKind    1
     * @param tableName 1
     * @return 1
     */
    public static String getColNameInfoSQL(String dbKind, String tableName) {
        String sql = null;
        switch (dbKind.toUpperCase()) {
            case "MYSQL":
                sql = "select c.column_name COLUMN_NAME,c.data_type DATA_TYPE,c.character_maximum_length DATA_LENGTH,CASE WHEN c.is_nullable = 'NO' THEN 'N' ELSE '' END AS NULLABLE,c.column_default DATA_DEFAULT,c.column_comment COMMENTS,CASE WHEN p.column_Name IS NULL THEN '' ELSE 'Y' END AS PK from information_schema.COLUMNS c LEFT JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS p ON c.table_schema = p.table_schema AND c.table_name = p.TABLE_NAME AND c.COLUMN_NAME = p.COLUMN_NAME AND p.constraint_name = 'PRIMARY' where c.table_name = '" + tableName + "' and c.table_schema = (select database())order by c.ordinal_position";
                break;
            case "ORACLE":
                sql = "SELECT utc.column_name COLUMN_NAME" +
                        "   ,utc.data_type DATA_TYPE" +
                        "   ,utc.data_length DATA_LENGTH" +
                        "   ,DECODE(utc.nullable, 'N', 'N', '') NULLABLE" +
                        "   ,utc.data_default DATA_DEFAULT" +
                        "   ,ucc.comments COMMENTS" +
                        "   ,CASE WHEN (SELECT LISTAGG(col.column_name, ',') WITHIN GROUP (ORDER BY col.column_name)" +
                        " FROM user_constraints con,user_cons_columns col " +
                        " WHERE con.constraint_name = col.constraint_name " +
                        "   AND con.constraint_type = 'P' " +
                        "   AND col.table_name = '" + tableName + "') LIKE '%' || UTC.COLUMN_NAME || '%' " +
                        "       THEN 'Y' ELSE '' END AS PK " +
                        "       FROM user_tab_columns utc,user_col_comments ucc " +
                        "       WHERE utc.table_name = ucc.table_name " +
                        "           AND utc.column_name = ucc.column_name " +
                        "           AND utc.table_name = '" + tableName + "' " +
                        "       ORDER BY column_id";
                break;
            case "SQLSERVER":
                sql = "select cast(a.name as varchar(100)) as COLUMN_NAME,cast(b.name as varchar(100)) as DATA_TYPE,cast(columnproperty(a.id, a.name, 'PRECISION') as varchar(100)) as DATA_LENGTH,cast(case when a.isnullable = 1 then '' else 'N' end as varchar(100)) as NULLABLE,cast(isnull(e.text, '') as varchar(100)) as DATA_DEFAULT,cast(isnull(g.[value], '') as varchar(100)) as COMMENTS,cast(case when t4.id is null then '' else 'Y' end as varchar(100)) as PK from sys.syscolumns a left join sys.SYSINDEXKEYS t4 on a.colid = t4.colid and t4.id = a.id left join sys.systypes b on a.xusertype = b.xusertype inner join sys.sysobjects d on a.id = d.id and d.xtype = 'U' and d.name <> 'dtproperties' left join sys.syscomments e on a.cdefault = e.id left join sys.extended_properties g on a.id = g.major_id and a.colid = g.minor_id left join sys.extended_properties f on d.id = f.major_id and f.minor_id = 0 where d.name = '" + tableName + "'";
                break;
            default:
        }
        return sql;
    }


}
