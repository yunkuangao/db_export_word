package com.dbexport.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 类说明:
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@Data
public class ResponseMessage implements Serializable {
    /**
     * 返回消息
     */
    private String message;
}
