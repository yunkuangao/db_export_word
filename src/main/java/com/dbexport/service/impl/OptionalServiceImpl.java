package com.dbexport.service.impl;

import com.dbexport.domain.OptionalPropertiesEnv;
import com.dbexport.service.IOptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @Author yuntian 317526763@qq.com
 * @Date 2019/8/13 09:54
 * @Version 1.0
 **/
@Service
public class OptionalServiceImpl implements IOptionalService {
    @Autowired
    private OptionalPropertiesEnv env;

    @Override
    public Map<String, Object> getOptional() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<String> param = Arrays.asList(env.getParam().split(","));
        List<String> paramCn = Arrays.asList(env.getParamCn().split(","));
        for (int i = 0; i < param.size(); i++) {
            map.put(param.get(i), paramCn.get(i));
        }
        return map;
    }

    @Override
    public List<String> getFiled() {
        Set<String> key = getOptional().keySet();
        return new ArrayList<>(key);
    }
}
