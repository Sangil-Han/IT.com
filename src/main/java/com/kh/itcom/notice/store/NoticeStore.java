package com.kh.itcom.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.notice.domain.Notice;

public interface NoticeStore {

	int selectCountNotice(SqlSession session);

	List<Notice> selectNoticeList(SqlSession session, PageInfo npi);

}
