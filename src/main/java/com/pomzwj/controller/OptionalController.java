package com.pomzwj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pomzwj.service.IOptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OptionalController
 * @Description TODO
 * @Author yuntian 317526763@qq.com
 * @Date 2019/8/13 09:47
 * @Version 1.0
 **/
@RestController
public class OptionalController {

    @Autowired
    private IOptionalService optionalService;

    @GetMapping("/getOptional")
    public String getOptional(){
        JSONObject json = new JSONObject(optionalService.getOptional());
        System.out.println(json);
        return json.toString();
    }
}
