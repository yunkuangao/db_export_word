package com.dbexport.utils;

/**
 * StringUtil工具类
 * @author yuntian 317526763@qq.com
 * @version 1.0
 * @date 2018/10/29/0029.
 */
public class StringUtils {
    /**
     * 得到object值
     * @param value value
     * @return 返回一个value.toString()
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:51
     **/
    public static String getValue(Object value) {
        return null == value ? "" : value.toString();
    }

}
