package com.kh.itcom.notice.service;

import java.util.List;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.notice.domain.Notice;

public interface NoticeService {

	int printTotalNoticeCount();

	List<Notice> printNoticeList(PageInfo npi);

}
