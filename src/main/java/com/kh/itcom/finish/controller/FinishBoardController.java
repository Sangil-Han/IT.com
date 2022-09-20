package com.kh.itcom.finish.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.finish.service.FinishBoardService;

@Controller
public class FinishBoardController {

	@Autowired
	private FinishBoardService fService;

	@RequestMapping(value = "/finish/registerView.do", method = RequestMethod.GET)
	public String registerView() {
		return "finish/finishRegisterForm";
	}

	@RequestMapping(value = "/finish/register.do")
	public ModelAndView registerBoard(ModelAndView mv, @ModelAttribute FinishBoard fBoard, HttpServletRequest request) {

		System.out.println(fBoard.toString());
		int result = fService.registerBoard(fBoard);
		mv.setViewName("finish/finishListView");
		return mv;
	}

	@RequestMapping(value = "/finish/listView.do", method = RequestMethod.GET)
	public ModelAndView listView(ModelAndView mv, @RequestParam(value = "page", required = false) Integer page) {
		// 페이징 처리
		int currentPage = (page != null) ? page : 1;
		int totalCount = fService.getTotalCount("", ""); // 총게시물수
		int boardLimit = 10; // 한 페이지의 게시물수
		int naviLimit = 5; // 한 번에 보여지는 페이지 수
		int maxPage; // 최대 페이지 넘버
		int startNavi; // 한 번에 보여지는 페이지에서 시작 넘버 1, 6, 11 등..
		int endNavi; // 한 번에 보여지는 페이지에서 마지막 넘버 5, 10, 15 등..
		maxPage = (int) ((double) totalCount / boardLimit + 0.9);
		startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
		endNavi = startNavi + naviLimit - 1;
		if (maxPage < endNavi) {
			endNavi = maxPage;
		}

		List<FinishBoard> fList = fService.printAllBoard(currentPage, boardLimit);
		if (!fList.isEmpty()) {
			mv.addObject("urlVal", "listView");
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("maxPage", maxPage);
			mv.addObject("fList", fList);
			mv.addObject("currentPage", currentPage);
		}
		mv.setViewName("finish/finishListView");
		return mv;
	}

	@RequestMapping(value ="/finish/search.do", method = RequestMethod.GET)
	public ModelAndView boardSearchList(ModelAndView mv, 
			@RequestParam("searchValue") String searchValue,
			@RequestParam("searchOption") String searchOption,
			@RequestParam(value = "page", required = false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int totalCount = fService.getTotalCount(searchOption, searchValue); // 총게시물수
		int boardLimit = 10; // 한 페이지의 게시물수
		int naviLimit = 5; // 한 번에 보여지는 페이지 수
		int maxPage; // 최대 페이지 넘버
		int startNavi; // 한 번에 보여지는 페이지에서 시작 넘버 1, 6, 11 등..
		int endNavi; // 한 번에 보여지는 페이지에서 마지막 넘버 5, 10, 15 등..
		maxPage = (int) ((double) totalCount / boardLimit + 0.9);
		startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
		endNavi = startNavi + naviLimit - 1;
		if (maxPage < endNavi) {
			endNavi = maxPage;
		}
		List<FinishBoard> fList = fService.printAllByValue(searchOption, searchValue, currentPage, boardLimit);
		if (!fList.isEmpty()) {
			mv.addObject("fList", fList);
		} else {
			mv.addObject("fList", null);
		}
		mv.addObject("urlVal", "search");
		mv.addObject("searchCondition", searchOption);
		mv.addObject("searchValue", searchValue);
		mv.addObject("fList", fList);
		mv.addObject("startNavi", startNavi);
		mv.addObject("endNavi", endNavi);
		mv.addObject("maxPage", maxPage);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("finish/finishListView");
		return mv;

	}

}
