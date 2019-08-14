package com.pomzwj.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OptionalPropertiesEnv
 * @Description TODO
 * @Author yuntian 317526763@qq.com
 * @Date 2019/8/13 10:45
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "optional")
@PropertySource("classpath:/application.yml")
public class OptionalPropertiesEnv {
    private String param;
    private String param_cn;

    private Map<String,String> map;

    public Map<String, String> getMap() {
        if(map==null){
            map = new HashMap<>(16);
        }
        if(map.size()==0){
            String[] pa = param.split(",");
            String[] pa_cn = param_cn.split(",");
            for(int i =0;i<param.length();i++){
                map.put(pa[i],pa_cn[i]);
            }
        }
        return map;
    }
}
