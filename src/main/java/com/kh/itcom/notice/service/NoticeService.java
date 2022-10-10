package com.kh.itcom.notice.service;

import java.util.List;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.notice.domain.Notice;

public interface NoticeService {

	// 공지사항 등록
	int registerNotice(Notice notice);
	// 공지사항 수정
	int modifyNotice(Notice notice);
	// 공지사항 삭제
	int removeNotice(int noticeNo);
	// 총 공지사항 수
	int printTotalNoticeCount();
	// 공지사항 목록
	List<Notice> printNoticeList(PageInfo npi);

	// 공지사항 상세 조회
	Notice printNoticeByNo(int noticeNo);

}
