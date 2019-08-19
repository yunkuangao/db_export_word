package com.dbexport.domain;

import com.deepoove.poi.data.RowRenderData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 类说明:Temp缓冲数据
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@Data
public class TempData implements Serializable {
    private String tableName;
    private String tableComment;
    private List<RowRenderData> data;
}
