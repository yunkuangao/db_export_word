package com.dbexport.controller;

import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;
import com.dbexport.domain.ResponseMessage;
import com.dbexport.officeframework.poitl.PoitlOperatorService;
import com.dbexport.service.IDataOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 类说明:
 *
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@RestController
public class DataOperatorController {

    @Autowired
    private IDataOperatorService dataOperatorService;
    @Autowired
    private PoitlOperatorService poitlOperatorService;
    @Value("${word.model.export}")
    private String filePath;


    @PostMapping(value = "/makeWord")
    @ResponseBody
    public ResponseMessage getData(String dbKind, DbBaseInfo info, HttpServletResponse response) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            List<DbTable> tableMessage = dataOperatorService.getTableName(dbKind, info);
            tableMessage = dataOperatorService.getTabsAllColumn(tableMessage, dbKind, info);
            poitlOperatorService.makeDoc(tableMessage, info);
            responseMessage.setMessage("生成文档成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage("诶，遇到错误了," + e.getMessage());
        }
        return responseMessage;
    }
}
