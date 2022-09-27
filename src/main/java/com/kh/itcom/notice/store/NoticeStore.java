package com.kh.itcom.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.notice.domain.Notice;

public interface NoticeStore {

	int selectCountNotice(SqlSession session);

	List<Notice> selectNoticeList(SqlSession session, PageInfo npi);

	int insertNoice(SqlSession session, Notice notice);

	Notice selectNoticeByNo(SqlSession session, int noticeNo);

	int deleteNotice(SqlSession session, int noticeNo);

	int updateNotice(SqlSession session, Notice notice);

}
