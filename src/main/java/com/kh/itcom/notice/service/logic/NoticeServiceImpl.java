package com.kh.itcom.notice.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.notice.domain.Notice;
import com.kh.itcom.notice.service.NoticeService;
import com.kh.itcom.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private NoticeStore nStore;
	
	// 공지사항 등록
	@Override
	public int registerNotice(Notice notice) {
		int result = nStore.insertNoice(session, notice);
		return result;
	}

	// 공지사항 수정
	@Override
	public int modifyNotice(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}

	// 공지사항 삭제
	@Override
	public int removeNotice(int noticeNo) {
		int result = nStore.deleteNotice(session, noticeNo);
		return result;
	}

	// 총 공지사항 수
	@Override
	public int printTotalNoticeCount() {
		int count = nStore.selectCountAllNotice(session);
		return count;
	}

	// 공지사항 목록
	@Override
	public List<Notice> printNoticeList(PageInfo npi) {
		List<Notice> nList = nStore.selectNoticeList(session, npi);
		return nList;
	}

	// 공지사항 상세 조회
	@Override
	public Notice printNoticeByNo(int noticeNo) {
		Notice notice = nStore.selectNoticeByNo(session, noticeNo);
		return notice;
	}

}
