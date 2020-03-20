package com.dbexport.controller;

import com.alibaba.fastjson.JSONObject;
import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;
import com.dbexport.service.IDataOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 获取数据表接口
 * @author yuntian 317526763@qq.com
 * @version 1.0
 * @date 2019年8月19日 09点22分
 **/
@RestController
public class DataBaseInfoController {

    @Autowired
    private IDataOperatorService dataOperatorService;

    private Predicate<List<String>> isNullOrEmpty = ((lt) -> {
        for (String str : lt) {
            if (str == null || str.length() == 0) {
                return true;
            }
        }
        return false;
    });

    /**
     * @param dbKind 数据库类型
     * @param info   参数
     * @return 返回json格式的数据表名
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:22
     **/
    @PostMapping("/getTable")
    public @ResponseBody
    String getTable(String dbKind, DbBaseInfo info) throws Exception {
        if (isNullOrEmpty.test(info.getDateBaseInfo()) || isNullOrEmpty.test(Collections.singletonList(dbKind))) {
            return "";
        }
        Map<String, Object> map = dataOperatorService.getTableName(dbKind, info)
                .stream()
                .collect(Collectors.toMap(DbTable::getTableName, DbTable::getTableName));
        return new JSONObject(map).toString();
    }
}
