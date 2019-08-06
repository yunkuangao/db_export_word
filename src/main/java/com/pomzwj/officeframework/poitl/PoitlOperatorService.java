package com.pomzwj.officeframework.poitl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.pomzwj.domain.DbTable;
import com.pomzwj.domain.SegmentData;
import com.pomzwj.domain.TempData;
import com.pomzwj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.util.*;


/**
 * 类说明:POI-TL操作服务
 *
 * @author zhaowenjie<1 5 1 3 0 4 1 8 2 0 @ qq.com>
 * @author yuntian 317526763@qq.com
 * @date 2018/10/29/0029.
 */
@Service
public class PoitlOperatorService {
    @Value("${word.model.export}")
    public String exportWord;
    @Value("${word.model.import}")
    public String importWord;
    @Value("${word.model.sub-model}")
    public String subModelWord;

    /**
     * 生成word
     *
     * @param tableMessage 表格内容
     * @throws Exception 异常
     */
    public void makeDoc(List<DbTable> tableMessage) throws Exception {


        List<TempData> tempDataList = new ArrayList<>();
        for (DbTable dbTable : tableMessage) {
            List<Map> data = (List<Map>) dbTable.getTabsColumn();
            TempData tempData = new TempData();
            tempData.setTableComment(dbTable.getTableComments());
            tempData.setTableName(dbTable.getTableName());

            List<RowRenderData> rowRenderDataList = new ArrayList<>();
            for (Map map : data) {

                //列名
                String columnName = StringUtils.getValue(map.get("COLUMN_NAME"));
                //数据类型
                String dataType = StringUtils.getValue(map.get("DATA_TYPE"));
                //数据长度
                String dataLength = StringUtils.getValue(map.get("DATA_LENGTH"));
                //是否可空
                String nullAble = StringUtils.getValue(map.get("NULL_ABLE"));
                //是否主键
                String pk = StringUtils.getValue(map.get("PK"));
                //数据缺省值
                String dataDefault = StringUtils.getValue(map.get("DATA_DEFAULT"));
                //字段注释
                String comments = StringUtils.getValue(map.get("COMMENTS"));

                RowRenderData labor = RowRenderData.build(columnName, dataType, dataLength, nullAble, pk, dataDefault, comments);

                rowRenderDataList.add(labor);
            }
            tempData.setData(rowRenderDataList);
            tempDataList.add(tempData);

        }
        Map<String, Object> tempMap = new HashMap<>();
        List<SegmentData> segmentDataList = new ArrayList<SegmentData>();
        for (TempData tempData : tempDataList) {
            RowRenderData header = RowRenderData.build("列名", "数据类型", "数据长度", "是否为空", "是否主键", "默认值", "备注");
            SegmentData segmentData = new SegmentData();
            segmentData.setTable(new MiniTableRenderData(header, tempData.getData()));
            segmentData.setTableName(tempData.getTableName());
            segmentData.setTableComments(tempData.getTableComment());
            segmentDataList.add(segmentData);
        }
        //Resource resource = new ClassPathResource(subModelWord);
        tempMap.put("seg", new DocxRenderData(ResourceUtils.getFile(subModelWord), segmentDataList));
        //Resource resource2 = new ClassPathResource(importWord);
        /*1.根据模板生成文档*/
        XWPFTemplate template = XWPFTemplate.compile(ResourceUtils.getFile(importWord)).render(tempMap);
        /*2.生成文档*/

        FileOutputStream out = new FileOutputStream(exportWord);
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }
}
