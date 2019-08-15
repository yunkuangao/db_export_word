
## 添加功能
2019年8月15日
给出当前库和当前用户的所有表（支持可选功能）

## 添加功能，输出内容自定义
 现在word的输出内容是一个可选项，而不是一个固定值(三大数据库适配)

## 将数据库表结构进行word输出（现只支持oracle）

## 预计增加功能
 mysql sqlserver的数据库增加，高可配数据表导出信息

## 修改了显示内容，增加主键列
修改时间 2019年8月6日
word内能够显示是否为主键

## 输出结构
表名 表说明 

| 列名 | 数据类型 | 数据长度 | 是否为空 | 是否主键 | 默认值 | 备注 |
|----|----|----|----|----|----|----|
|column_name|data_type|data_length|nullable|PK|default_value|conment|


 注：sqlserver必须手动输入ip，不然会出现ipv6访问不了的异常

 将数据库表结构进行word输出（支持oracle,mysql,sqlserver）

 访问地址http://localhost:8080

旧式访问地址，不可使用 ~~http://localhost:8080/dbExport/web/index.html~~ 