package com.kh.itcom.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.notice.domain.Notice;
import com.kh.itcom.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService nService;

	// 공지사항 작성 화면
	@RequestMapping(value = "/notice/writeView.do", method = RequestMethod.GET)
	public String noticeWriteView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
		if (loginAdmin != null) {
			return "notice/write";
		} else {
			return "redirect:/user/loginView.do";
		}
	}

	// 공지사항 등록
	@RequestMapping(value = "/notice/write.do", method = RequestMethod.POST)
	public ModelAndView noticeWrite(HttpServletRequest request, @ModelAttribute Notice notice,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
			if (loginAdmin != null) {
				String noticeFileName = uploadFile.getOriginalFilename();
				if (!uploadFile.getOriginalFilename().equals("")) {
					String root = request.getSession().getServletContext().getRealPath("resources");
					String savePath = root + "/files/notice";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String noticeFileRename = sdf.format(new Date(System.currentTimeMillis())) + "."
							+ noticeFileName.substring(noticeFileName.lastIndexOf(".") + 1);
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdir();
					}
					String noticeFilepath = savePath + "/" + noticeFileRename;
					uploadFile.transferTo(new File(noticeFilepath));
					notice.setNoticeFileName(noticeFileName);
					notice.setNoticeFileRename(noticeFileRename);
					notice.setNoticeFilePath(noticeFilepath);
				}
				int result = nService.registerNotice(notice);
				if (result > 0) {
					mv.setViewName("redirect:/notice/boardView.do");
				}
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		return mv;
	}

	// 공지사항 수정 화면
	@RequestMapping(value = "/notice/modifyView.do", method = RequestMethod.GET)
	public ModelAndView noticeModifyView(HttpServletRequest request, @RequestParam("noticeNo") int noticeNo,
			@RequestParam("page") Integer page, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
			if (loginAdmin != null) {
				Notice notice = nService.printNoticeByNo(noticeNo);
				if (notice != null) {
					mv.addObject("notice", notice);
					mv.addObject("page", page);
					mv.setViewName("notice/modify");
				} else {
					System.out.println("수정 화면 불러오기 실패");
				}
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		return mv;
	}

	// 공지사항 수정
	@RequestMapping(value = "/notice/modify.do", method = RequestMethod.POST)
	public ModelAndView noticeModify(HttpServletRequest request, @ModelAttribute Notice notice,
			@RequestParam(value = "file", required = false) MultipartFile reloadFile,
			@RequestParam("page") Integer page, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
			if (loginAdmin != null) {
				String noticeFileName = reloadFile.getOriginalFilename();
				if (!reloadFile.getOriginalFilename().equals("")) {
					String root = request.getSession().getServletContext().getRealPath("resources");
					String savePath = root + "/files/notice";
					File file = new File(savePath + "/" + notice.getNoticeFileRename());
					if (file.exists()) {
						file.delete();
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String noticeFileRename = sdf.format(new Date(System.currentTimeMillis())) + "."
							+ noticeFileName.substring(noticeFileName.lastIndexOf(".") + 1);
					String noticeFilepath = savePath + "/" + noticeFileRename;
					reloadFile.transferTo(new File(noticeFilepath));
					notice.setNoticeFileName(noticeFileName);
					notice.setNoticeFileRename(noticeFileRename);
					notice.setNoticeFilePath(noticeFilepath);
					int result = nService.modifyNotice(notice);
					if (result > 0) {
						mv.setViewName("redirect:/notice/detailView.do?noticeNo=" + notice.getNoticeNo() + "&page=" + page);
					}
				} else {
					mv.setViewName("redirect:/user/loginView.do");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		return mv;
	}

	// 공지사항 삭제
	@RequestMapping(value = "/notice/remove.do", method = RequestMethod.POST)
	public ModelAndView noticeRemove(HttpServletRequest request, @RequestParam("noticeNo") int noticeNo,
			ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
			if (loginAdmin != null) {
				int result = nService.removeNotice(noticeNo);
				if (result > 0) {
					mv.setViewName("redirect:/notice/boardView.do");
				} else {
					System.out.println("공지사항 삭제 실패");
				}
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
	
		}
		return mv;
	}

	// 공지사항 게시판
	@RequestMapping(value = "/notice/boardView.do", method = RequestMethod.GET)
	public ModelAndView noticeBoardView(HttpServletRequest request,
			@RequestParam(value = "page", required = false) Integer page, ModelAndView mv) {
		try {
			PageInfo npi = new PageInfo();
			npi.setCurrentPage((page != null) ? page : 1);
			npi.setRowCount(nService.printTotalNoticeCount()); // TODO:
			npi.setRowLimit(10);
			npi.setPageLimit(5);
			npi.setPageCount((int) ((double) npi.getRowCount() / npi.getRowLimit() + 0.9));
			npi.setStartPage(
					((int) ((double) npi.getCurrentPage() / npi.getPageLimit() + 0.9) - 1) * npi.getPageLimit() + 1);
			npi.setEndPage(npi.getStartPage() + npi.getPageLimit() - 1);
			if (npi.getPageCount() < npi.getEndPage()) {
				npi.setEndPage(npi.getPageCount());
			}
			List<Notice> nList = nService.printNoticeList(npi);
			if (!nList.isEmpty()) {
				mv.addObject("nList", nList);
				mv.addObject("npi", npi);
			}
			mv.setViewName("notice/board");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		return mv;
	}

	// 공지사항 상세 조회
	@RequestMapping(value = "/notice/detailView.do", method = RequestMethod.GET)
	public ModelAndView noticeDetailView(@RequestParam("noticeNo") int noticeNo, @RequestParam("page") Integer page,
			ModelAndView mv) {
		try {
			Notice notice = nService.printNoticeByNo(noticeNo);
			mv.addObject("notice", notice);
			mv.addObject("page", page);
			mv.setViewName("notice/detail");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		return mv;
	}

}
