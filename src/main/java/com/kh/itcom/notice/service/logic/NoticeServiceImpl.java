package com.kh.itcom.notice.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.notice.domain.Notice;
import com.kh.itcom.notice.service.NoticeService;
import com.kh.itcom.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private NoticeStore nStore;
	
	// 공지사항 개수
	@Override
	public int printTotalNoticeCount() {
		int count = nStore.selectCountNotice(session);
		return count;
	}

	@Override
	public List<Notice> printNoticeList(PageInfo npi) {
		List<Notice> nList = nStore.selectNoticeList(session, npi);
		return nList;
	}
	
	

}
