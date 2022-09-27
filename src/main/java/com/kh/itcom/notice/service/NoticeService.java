package com.kh.itcom.notice.service;

import java.util.List;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.notice.domain.Notice;

public interface NoticeService {

	int printTotalNoticeCount();

	List<Notice> printNoticeList(PageInfo npi);

	// 공지사항 등록
	int registerNotice(Notice notice);

	Notice printNoticeByNo(int noticeNo);

	int removeNotice(int noticeNo);

	int modifyNotice(Notice notice);

}
