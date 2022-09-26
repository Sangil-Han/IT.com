package com.kh.itcom.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.notice.domain.Notice;
import com.kh.itcom.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService nService;

	// 공지사항 작성 화면
	@RequestMapping(value = "/notice/writeView.do", method = RequestMethod.GET)
	public String noticeWriteView() {
		return "notice/write";
	}
	
	@RequestMapping(value = "/notice/boardView.do", method = RequestMethod.GET)
	public String noticeBoardView() {
		return "notice/board";
	}
	
	// 공지사항 게시판
//	@RequestMapping(value = "/notice/boardView.do", method = RequestMethod.GET)
//	public ModelAndView noticeBoardView(HttpServletRequest request,
//			@RequestParam(value = "page", required = false) Integer page, ModelAndView mv) {
//		try {
//			PageInfo npi = new PageInfo();
//			npi.setCurrentPage((page != null) ? page : 1);
//			npi.setRowCount(nService.printTotalNoticeCount()); // TODO:
//			npi.setRowLimit(5);
//			npi.setPageLimit(5);
//			npi.setPageCount((int) ((double) npi.getRowCount() / npi.getRowLimit() + 0.9));
//			npi.setStartPage(
//					((int) ((double) npi.getCurrentPage() / npi.getPageLimit() + 0.9) - 1) * npi.getPageLimit() + 1);
//			npi.setEndPage(npi.getStartPage() + npi.getPageLimit() - 1);
//			if (npi.getPageCount() < npi.getEndPage()) {
//				npi.setEndPage(npi.getPageCount());
//			}
//			List<Notice> nList = nService.printNoticeList(npi);
//			if (!nList.isEmpty()) {
//				mv.addObject("nList", nList);
//				mv.addObject("npi", npi);
//				mv.setViewName("notice/board");
//			} else {
//				System.out.println("공지사항 게시판 조회 실패");
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			System.out.println(e.toString());
//		}
//		return mv;
//	}

}
