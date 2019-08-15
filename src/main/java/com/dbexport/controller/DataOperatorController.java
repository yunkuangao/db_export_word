package com.dbexport.controller;

import com.alibaba.fastjson.JSON;
import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;
import com.dbexport.domain.ResponseMessage;
import com.dbexport.officeframework.poitl.PoitlOperatorService;
import com.dbexport.service.IDataOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类说明:
 *
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@Controller
public class DataOperatorController {

    @Autowired
    private IDataOperatorService dataOperatorService;
    @Autowired
    private PoitlOperatorService poitlOperatorService;
    @Value("${word.model.export}")
    private String filePath;

    @RequestMapping(value = "/makeWord")
    public @ResponseBody
    ResponseMessage getData(String dbKind, DbBaseInfo info) throws IOException, InterruptedException {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            List<DbTable> tableMessage = dataOperatorService.getTableName(dbKind,info);
            tableMessage = tableMessage
                    .stream()
                    .parallel()
                    .filter(lt -> info.getTable().contains(lt.getTableName()))
                    .collect(Collectors.toList());
            for (DbTable dbTable : tableMessage) {
                List<Map> tabsColumn = dataOperatorService.getTabsColumn(dbKind, info, dbTable.getTableName());
                dbTable.setTabsColumn(tabsColumn);
            }
            poitlOperatorService.makeDoc(tableMessage,info);
            responseMessage.setMessage("生成文档成功!!!");
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("诶，遇到错误了,"+e.getMessage());
        }
        System.out.println(JSON.toJSONString(responseMessage));
        return responseMessage;
    }
}
