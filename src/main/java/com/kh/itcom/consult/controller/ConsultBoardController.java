package com.kh.itcom.consult.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.consult.domain.ConsultBoard;
import com.kh.itcom.consult.service.ConsultBoardService;

@Controller
public class ConsultBoardController {
	@Autowired
	private ConsultBoardService cService;
	
	@RequestMapping(value="/cBoard/consultWriteFormView.do", method=RequestMethod.GET)
	public String showBoardWriteForm() {
		return "cBoard/boardWriteForm";
	}
	
	@RequestMapping(value="/cBoard/consultList.do", method=RequestMethod.GET)
	public ModelAndView boardListView(
			ModelAndView mv
			, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int totalCount = cService.getTotalCount("","");
		int boardLimit = 10;
		int naviLimit = 5;
		int maxPage;
		int startNavi;
		int endNavi;
		// 23/5 = 4.8 + 0.9 = 5(.7)
		maxPage = (int)((double)totalCount/boardLimit + 0.9);
		startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
		endNavi = startNavi + naviLimit - 1;
		if(maxPage < endNavi) {
			endNavi = maxPage;
		}
		List<ConsultBoard> cList = cService.printAllBoard(currentPage, boardLimit);
		if(!cList.isEmpty()) {
			mv.addObject("urlVal", "consultList");
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("cList",cList);
			mv.setViewName("cBoard/consultListView");
		}
		return mv;
	}
	@RequestMapping(value="/cBoard/consultDetailView.do", method=RequestMethod.GET)
	public ModelAndView boardDetailView(
			ModelAndView mv
			, @RequestParam("cBoardNo") Integer cBoardNo
			, @RequestParam("page") Integer page) {
		try {
			ConsultBoard cBoard = cService.printOneByNo(cBoardNo);
			mv.addObject("cBoard", cBoard);
			mv.addObject("page", page);
			mv.setViewName("cBoard/detailView");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/cBoard/consultSearch.do", method=RequestMethod.GET)
	public ModelAndView boardSerachList(
			ModelAndView mv
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = cService.getTotalCount(searchCondition, searchValue);
			int boardLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			maxPage = (int)((double)totalCount/boardLimit + 0.9);
			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
			endNavi = startNavi + naviLimit - 1;
			if(maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<ConsultBoard> cList = cService.printAllByValue(
					searchCondition, searchValue, currentPage, boardLimit);
			if(!cList.isEmpty()) {
				mv.addObject("cList", cList);
			}else {
				mv.addObject("cList", null);
			}
			mv.addObject("urlVal", "consultSearch");
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("searchValue", searchValue);
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.setViewName("cBoard/consultListView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping(value="/cBoard/consultRegister.do", method=RequestMethod.POST)
	public ModelAndView registBoard(
			ModelAndView mv
			, @ModelAttribute ConsultBoard cBoard
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request) {
		try {
			String cBoardFileName = uploadFile.getOriginalFilename();
			if(!cBoardFileName.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\cBoardUploadFile";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String cBoardFileRename
				= sdf.format(new Date(System.currentTimeMillis()))+"."
						+cBoardFileName.substring(cBoardFileName.lastIndexOf(".")+1);
				File file = new File(savePath);
				if(!file.exists()) {
					file.mkdir();
				}
				uploadFile.transferTo(new File(savePath+"\\"+cBoardFileRename));
				// 파일을 buploadFile 경로에 저장하는 메소드
				String cBoardFilePath = savePath+"\\"+cBoardFileRename;
				cBoard.setcBoardFileName(cBoardFileName);
				cBoard.setcBoardFileRename(cBoardFileRename);
				cBoard.setcBoardFilePath(cBoardFilePath);
			}
			int result = cService.registerBoard(cBoard);
			mv.setViewName("redirect:/cBoard/consultList.do");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
