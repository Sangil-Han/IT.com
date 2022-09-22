package com.kh.itcom.point.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.point.domain.PointHistory;
import com.kh.itcom.point.service.PointService;
import com.kh.itcom.user.domain.User;

@Controller
public class PoinController {

	@Autowired
	private PointService pService;

	/**
	 * 포인트 내역
	 * @param request
	 * @param page
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/point/historyView.do", method = RequestMethod.GET)
	public ModelAndView pointHistoryView(HttpServletRequest request,
			@RequestParam(value = "page", required = false) Integer page, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginUser");
			if (user != null) {
				int currentPage = (page != null) ? page : 1;
				int pointCount = pService.printTotalPointCount(user.getUserId());
				int pointLimit = 10;
				int pageLimit = 5;
				int pageCount = (int) ((double) pointCount / pointLimit + 0.9);
				int startPage = ((int) ((double) currentPage / pageLimit + 0.9) - 1) * pageLimit + 1;
				int endPage = startPage + pageLimit - 1;
				if (pageCount < endPage) {
					endPage = pageCount;
				}
				HashMap<String, Integer> pageInfo = new HashMap<String, Integer>() {
					{
						put("currentPage", currentPage);
						put("pointLimit", pointLimit);
					}
				};
				List<PointHistory> phList = pService.printPointHistory(user.getUserId(), pageInfo);
				if (!phList.isEmpty()) {
					mv.addObject("phList", phList);
					mv.addObject("pointCount", pointCount);
					mv.addObject("pointLimit", pointLimit);
					mv.addObject("currentPage", currentPage);
					mv.addObject("startPage", startPage);
					mv.addObject("endPage", endPage);
				}
				mv.setViewName("user/pointHistory");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mv;
	}

}
