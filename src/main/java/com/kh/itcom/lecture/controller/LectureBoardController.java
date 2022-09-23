package com.kh.itcom.lecture.controller;

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

import com.kh.itcom.lecture.domain.LectureBoard;
import com.kh.itcom.lecture.service.LectureBoardService;

@Controller
public class LectureBoardController {
	@Autowired
	private LectureBoardService lbService;
	
	// 게시글 등록 화면
	@RequestMapping(value="/lecture/writeView.do", method=RequestMethod.GET)
	public String showlectureWrite() {
		return "lecture/lectureWriteForm";
	}
	
	// 게시글 등록
	@RequestMapping(value="/lecture/register.do", method=RequestMethod.POST)
	public ModelAndView registLectureBoard(
			ModelAndView mv
			, @ModelAttribute LectureBoard lectureboard
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			,HttpServletRequest request) {
		try {
			String lBoardFileName = uploadFile.getOriginalFilename();
			if(!lBoardFileName.equals("")) {
				///////////////////////////////////////////////////////////////////////////
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\buploadFiles";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String lBoardFileRename 
				= sdf.format(new Date(System.currentTimeMillis()))+"."
						+lBoardFileName.substring(lBoardFileName.lastIndexOf(".")+1);
				// 1.png, img.png
				File file = new File(savePath);
				if(!file.exists()) {
					file.mkdir();
				}
				/////////////////////////////////////////////////////////////////////////////
				uploadFile.transferTo(new File(savePath+"\\"+lBoardFileRename)); 
				// 파일을 buploadFiles 경로에 저장하는 메소드
				String lBoardFilePath = savePath+"\\"+lBoardFileRename;
				lectureboard.setlBoardFileName(lBoardFileName);
				lectureboard.setlBoardFileRename(lBoardFileRename);
				lectureboard.setlBoardFilePath(lBoardFilePath);
			}
			int result = lbService.registerLecture(lectureboard);
			mv.setViewName("redirect:/lecture/list.do");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 수정 화면
	@RequestMapping(value="/lecture/modifyView.do", method=RequestMethod.GET)
	public ModelAndView lectureModifyView(
			ModelAndView mv
			,@RequestParam("lBoardNo") Integer lBoardNo
			,@RequestParam("page") int page) {
		try {
			LectureBoard lectureboard = lbService.printOneByNo(lBoardNo);
			mv.addObject("lectureBoard", lectureboard);
			mv.addObject("page", page);
			mv.setViewName("lecture/lectureModifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 수정
	@RequestMapping(value="/lecture/modify.do", method=RequestMethod.POST)
	public ModelAndView lectureboardModify(
			@ModelAttribute LectureBoard lectureboard
			, ModelAndView mv
			,@RequestParam(value="reloadFile", required=false) MultipartFile reloadFile
			,@RequestParam("page") Integer page
			,HttpServletRequest request) {
		try {
			String lBoardFileName = reloadFile.getOriginalFilename();
			if(reloadFile != null && !lBoardFileName.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\buploadFiles";
				File file = new File(savedPath + "\\" + lectureboard.getlBoardFileRename());
				if(file.exists()) {
					file.delete();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String lBoardFileRename = sdf.format(new Date(System.currentTimeMillis()))
						+ "." + lBoardFileName.substring(lBoardFileName.lastIndexOf(".")+1);
				String lBoardFilePath = savedPath + "\\" + lBoardFileRename;
				reloadFile.transferTo(new File(lBoardFilePath));
				lectureboard.setlBoardFileName(lBoardFileName);
				lectureboard.setlBoardFileRename(lBoardFileRename);
				lectureboard.setlBoardFilePath(lBoardFilePath);
			}
			int result = lbService.modifyLecture(lectureboard);
			mv.setViewName("redirect:/lecture/list.do?page="+page);
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 전체 조회
	@RequestMapping(value="/lecture/list.do", method=RequestMethod.GET)
	public ModelAndView lectureBoardListView(
			ModelAndView mv
			,@RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int totalCount = lbService.getTotalCount("","");
		int lboardLimit = 10;
		int naviLimit = 5;
		int maxPage;
		int startNavi;
		int endNavi;
		maxPage = (int)((double)totalCount/lboardLimit + 0.9);
		startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
		endNavi = startNavi + naviLimit - 1;
		if(maxPage < endNavi) {
			endNavi = maxPage;
		}
		List<LectureBoard> lbList = lbService.printAllLectureBoard(currentPage, lboardLimit);
		if(!lbList.isEmpty()) {
			mv.addObject("urlVal", "list");
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("lbList", lbList);
		}
		mv.setViewName("lecture/lecturelistView");
		return mv;
	}
	
	// 게시글 상세 조회
	@RequestMapping(value="/lecture/detail.do", method=RequestMethod.GET)
	public ModelAndView lectureBoardDetailView(
			ModelAndView mv
			, @RequestParam("lBoardNo") Integer lBoardNo
			, @RequestParam("page") Integer page
			, HttpSession session) {
		try {
			
			LectureBoard lectureboard = lbService.printOneByNo(lBoardNo);
			session.setAttribute("lBoardNo", lectureboard.getlBoardNo());
			mv.addObject("lectureBoard", lectureboard);
			mv.addObject("page", page);
			mv.setViewName("lecture/lectureDetailView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 조건 검색
	@RequestMapping(value="/lecture/search.do", method=RequestMethod.GET)
	public ModelAndView lectureBoardSearchList(
			ModelAndView mv
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = lbService.getTotalCount(searchCondition, searchValue);
			int lboardLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			maxPage = (int)((double)totalCount/lboardLimit + 0.9);
			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
			endNavi = startNavi + naviLimit - 1;
			if(maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<LectureBoard> lbList = lbService.printAllByValue(
					searchCondition, searchValue, currentPage, lboardLimit);
			if(!lbList.isEmpty()) {
				mv.addObject("lbList", lbList);
			}else {
				mv.addObject("lbList", null);
			}
			mv.addObject("urlVal", "search");
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("searchValue", searchValue);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.setViewName("lecture/lecturelistView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
}
