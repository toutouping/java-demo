package com.ssm.promotion.core.service.impl;

import javax.annotation.Resource;

import com.ssm.promotion.core.entity.NoticeExport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ssm.promotion.core.dao.NoticeExportDao;
import com.ssm.promotion.core.service.NoticeExportService;

/**
 * @author llp
 * @project_name java-demo
 * @date 2018-08-08
 */
@Service("noticeExportService")
public class NoticeExportServiceImpl implements NoticeExportService {
    private static final Logger log = Logger.getLogger(NoticeExportServiceImpl.class);// 日志文件

    @Resource
    private NoticeExportDao noticeExportDao;

	@Override
	public int insertWord(NoticeExport noticeExport) {
		if(noticeExportDao.insertWord(noticeExport) > 0) {
			log.info("NoticeExportServiceImpl insertWord insert word success");
			return 1;
		} else {
			return 0;
		}
	}

}
