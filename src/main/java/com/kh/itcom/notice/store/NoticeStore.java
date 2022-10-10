package com.kh.itcom.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.notice.domain.Notice;

public interface NoticeStore {
	// 공지사항 등록
	int insertNoice(SqlSession session, Notice notice);

	// 공지사항 수정
	int updateNotice(SqlSession session, Notice notice);

	// 공지사항 삭제
	int deleteNotice(SqlSession session, int noticeNo);

	// 총 공지사항 수
	int selectCountAllNotice(SqlSession session);

	// 공지사항 목록
	List<Notice> selectNoticeList(SqlSession session, PageInfo npi);

	// 공지사항 상세 조회
	Notice selectNoticeByNo(SqlSession session, int noticeNo);

}
