package com.kh.itcom.notice.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.notice.domain.Notice;
import com.kh.itcom.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {
	
	// 공지사항 개수
	@Override
	public int selectCountNotice(SqlSession session) {
		int count = session.selectOne("NoticeMapper.selectCountNotice");
		return count;
	}

	@Override
	public List<Notice> selectNoticeList(SqlSession session, PageInfo npi) {
		int offset = (npi.getCurrentPage() - 1) * npi.getRowLimit();
		RowBounds rowBounds = new RowBounds(offset, npi.getRowLimit());
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}
	
}
