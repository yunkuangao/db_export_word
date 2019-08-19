package com.dbexport.domain;

import com.deepoove.poi.data.MiniTableRenderData;
import lombok.Data;

import java.io.Serializable;

/**
 * 类说明:子文档的数据结构
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@Data
public class SegmentData implements Serializable {
    /**
     * 表结构
     */
    MiniTableRenderData table;
    /**
     * 表名
     */
    String tableName;
    /**
     * 表注释
     */
    String tableComments;
}
