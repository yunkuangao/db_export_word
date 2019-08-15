package com.dbexport.controller;

import com.alibaba.fastjson.JSONObject;
import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;
import com.dbexport.service.IDataOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @ClassName DBInfoController
 * @Description TODO
 * @Author yuntian 317526763@qq.com
 * @Date 2019/8/15 11:09
 * @Version 1.0
 **/
@RestController
public class DBInfoController {

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

    @PostMapping("/getTable")
    public @ResponseBody
    String getTable(String dbKind, DbBaseInfo info) throws Exception {
        if (isNullOrEmpty.test(info.getDBInfo()) || isNullOrEmpty.test(Collections.singletonList(dbKind))) {
            return "";
        }
        Map<String, Object> map = dataOperatorService.getTableName(dbKind, info)
                .stream()
                .collect(Collectors.toMap(DbTable::getTableName, DbTable::getTableName));
        return new JSONObject(map).toString();
    }
}
