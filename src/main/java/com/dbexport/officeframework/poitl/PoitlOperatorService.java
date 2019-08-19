package com.dbexport.officeframework.poitl;

import com.dbexport.domain.*;
import com.dbexport.utils.StringUtils;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * POI-TL操作服务
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
    @Autowired
    private OptionalPropertiesEnv env;

    /**
     * 生成word
     * @param tableMessage 表内容
     * @param info         参数
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:42
     **/
    public void makeDoc(List<DbTable> tableMessage, DbBaseInfo info) throws Exception {
        List<String> key = info.getOptional();
        List<TempData> tempDataList = new ArrayList<>();
        for (DbTable dbTable : tableMessage) {
            List<Map> data = dbTable.getTabsColumn();
            TempData tempData = new TempData();
            tempData.setTableComment(dbTable.getTableComments());
            tempData.setTableName(dbTable.getTableName());

            List<RowRenderData> rowRenderDataList = new ArrayList<>();
            for (Map map : data) {
                List<String> currentList = new ArrayList<>(16);
                for (String str : key) {
                    currentList.add(StringUtils.getValue(map.get(str)));
                }
                RowRenderData labor = RowRenderData.build(currentList.toArray(new String[0]));
                rowRenderDataList.add(labor);
            }
            tempData.setData(rowRenderDataList);
            tempDataList.add(tempData);

        }
        Map<String, Object> tempMap = new HashMap<>();
        List<SegmentData> segmentDataList = new ArrayList<>();
        Map<String, String> map = env.getMap();
        for (TempData tempData : tempDataList) {
            RowRenderData header = RowRenderData.build(key.stream().map(map::get).toArray(String[]::new));
            SegmentData segmentData = new SegmentData();
            segmentData.setTable(new MiniTableRenderData(header, tempData.getData()));
            segmentData.setTableName(tempData.getTableName());
            segmentData.setTableComments(tempData.getTableComment());
            segmentDataList.add(segmentData);
        }
        tempMap.put("seg", new DocxRenderData(ResourceUtils.getFile(subModelWord), segmentDataList));
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