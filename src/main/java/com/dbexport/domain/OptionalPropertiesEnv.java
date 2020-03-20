package com.dbexport.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 展示列上下文环境
 * @Author yuntian 317526763@qq.com
 * @Date 2019/8/13 10:45
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "optional")
@PropertySource("classpath:/application.yml")
public class OptionalPropertiesEnv {
    /**
     * 列名
     */
    private String param;
    /**
     * 列名（中文显示）
     */
    private String paramCn;
    /**
     * 列名map
     */
    private Map<String, String> map;

    /**
     * 获取列名
     * @return 返回一个基于当前上下文的展示列map
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:37
     **/
    public Map<String, String> getMap() {
        if (map == null) {
            map = new HashMap<>(16);
        }
        if (map.size() == 0) {
            String[] pa = param.split(",");
            String[] paCn = paramCn.split(",");
            for (int i = 0, j = pa.length; i < j; ++i) {
                map.put(pa[i], paCn[i]);
            }
        }
        return map;
    }
}
