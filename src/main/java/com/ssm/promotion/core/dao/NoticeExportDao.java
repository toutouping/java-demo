package com.ssm.promotion.core.dao;

import org.springframework.stereotype.Repository;

import com.ssm.promotion.core.entity.NoticeExport;

@Repository
public interface NoticeExportDao {

	/**
	 * 插入word信息
	 * @param noticeExport
	 * @return
	 */
	public int insertWord(NoticeExport noticeExport);
}
