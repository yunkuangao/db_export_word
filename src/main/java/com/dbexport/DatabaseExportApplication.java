package com.dbexport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuntian 317526763@qq.com
 */
@EnableConfigurationProperties
@SpringBootApplication
@Controller
public class DatabaseExportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseExportApplication.class, args);
    }

    /**
     * 给出一个默认页面
     * @return resource/static下的路径
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:07
     **/
    @RequestMapping("/")
    String hello() {
        return "web/index.html";
    }
}
