package com.dbexport.officeframework.poitl;

import com.dbexport.domain.DbBaseInfo;
import com.dbexport.domain.DbTable;
import com.dbexport.domain.SegmentData;
import com.dbexport.domain.TempData;
import com.dbexport.utils.StringUtils;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
    @Value("${word.model.import}")
    public String importWord;
    @Value("${word.model.sub-model}")
    public String subModelWord;
    @Value("${word.model.export}")
    public String exportWord;

    /**
     * 生成word
     * @param tableMessage 表内容
     * @param info         参数
     * @author yuntian 317526763@qq.com
     * @date 2019/8/19 09:42
     **/
    public File makeDoc(List<DbTable> tableMessage, DbBaseInfo info) throws Exception {
        List<String> key = info.getOptional();
        List<TextRenderData> headerData = new ArrayList<>();
        for (String str : key) {
            headerData.add(new TextRenderData(str));
        }
        List<TempData> tempDataList = new ArrayList<>();
        for (DbTable dbTable : tableMessage) {
            List<Map> data = dbTable.getTabsColumn();
            TempData tempData = new TempData();
            tempData.setTableComment(dbTable.getTableComments());
            tempData.setTableName(dbTable.getTableName());

            List<RowRenderData> rowRenderDataList = new ArrayList<>();
            for (Map map : data) {
                List<TextRenderData> currentList = new ArrayList<>();

                for (String str : key) {
                    TextRenderData current = new TextRenderData();
                    current.setText(StringUtils.getValue(map.get(str)));
                    currentList.add(current);
                }
                RowRenderData labor = null;
                if (currentList.stream().noneMatch(lt -> "主键".equals(lt.getText()))) {
                    labor = RowRenderData.build(currentList.toArray(new TextRenderData[0]));
                } else {
                    labor = new RowRenderData(currentList, "FFDEAD");
                }
                rowRenderDataList.add(labor);
            }
            tempData.setData(rowRenderDataList);
            tempDataList.add(tempData);
        }
        Map<String, Object> tempMap = new HashMap<>(16);
        List<SegmentData> segmentDataList = new ArrayList<>();
        for (TempData tempData : tempDataList) {
            RowRenderData header = new RowRenderData(headerData, "A9A9A9");
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
        File exportFile = new File(exportWord);
        OutputStream os = new FileOutputStream(exportFile);
        template.write(os);
        os.flush();
        os.close();
        template.close();
        return exportFile;
    }
}