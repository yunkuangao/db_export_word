package com.dbexport.controller;

import com.alibaba.fastjson.JSONObject;
import com.dbexport.service.IOptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 展示列的列表
 * @Author yuntian 317526763@qq.com
 * @Date 2019/8/13 09:47
 * @Version 1.0
 **/
@RestController
public class OptionalController {

    @Autowired
    private IOptionalService optionalService;

    /**
     * 展示列
     * @return 返回一个展示列的json
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:24
     **/
    @GetMapping("/getOptional")
    public String getOptional() {
        JSONObject json = new JSONObject(optionalService.getOptional());
        return json.toString();
    }
}
