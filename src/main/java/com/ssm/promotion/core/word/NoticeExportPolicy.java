package com.ssm.promotion.core.word;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 总行信息技术部系统维护通知书
 */
public class NoticeExportPolicy  extends DynamicTableRenderPolicy {

    private static Logger logger = LoggerFactory.getLogger(DetailTablePolicy.class);

    public static XWPFWordUtil xwpfHelper = new XWPFWordUtil();

    @Override
    public void render(XWPFTable table, Object data) {
        if(null == data) {

        }
        return;
    }


}