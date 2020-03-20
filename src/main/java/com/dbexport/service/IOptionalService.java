package com.dbexport.service;
/**
 * @author yuntian 317526763@qq.com
 * @date 2020/3/20 15:27
 **/

import java.util.List;
import java.util.Map;

public interface IOptionalService {
    /**
     * 获取展示列
     * @return 返回展示列
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:45
     **/
    Map<String, Object> getOptional();

    /**
     * 获取展示列
     * @return 返回展示列
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:46
     **/
    List<String> getFiled();
}
