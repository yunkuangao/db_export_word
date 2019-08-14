package com.pomzwj.domain;

import java.io.Serializable;

/**
 * 类说明:
 *
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
public class ResponseMessage implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
