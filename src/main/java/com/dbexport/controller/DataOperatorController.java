package com.dbexport.controller;

import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;
import com.dbexport.domain.ResponseMessage;
import com.dbexport.officeframework.poitl.PoitlOperatorService;
import com.dbexport.service.IDataOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 进行文档生成的对外接口
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


    /**
     * 根据传进来的参数进行文档的生成
     * @param dbKind 数据库类型
     * @param info   参数
     * @return 返回消息提示
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:15
     **/
    @PostMapping(value = "/makeWord")
    @ResponseBody
    public ResponseMessage getData(String dbKind, DbBaseInfo info) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            List<DbTable> tableMessage = dataOperatorService.getTabsAllColumn(dbKind, info);
            poitlOperatorService.makeDoc(tableMessage, info);
            responseMessage.setMessage("生成文档成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage("诶，遇到错误了," + e.getMessage());
        }
        return responseMessage;
    }
}
